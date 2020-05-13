package com.example.daggerarchtddtrial.activities.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerarchtddtrial.MyApplication;
import com.example.daggerarchtddtrial.R;
import com.example.daggerarchtddtrial.activities.common.BaseActivity;
import com.example.daggerarchtddtrial.activities.common.PresenterWrapper;
import com.example.daggerarchtddtrial.activities.common.UtilWorks;
import com.example.daggerarchtddtrial.common.ApplicationConstants;
import com.example.daggerarchtddtrial.common.di.presentationleveldi.PresentationComponent;
import com.example.daggerarchtddtrial.networking.UserSchema;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @Inject
    protected UtilWorks utilWorks;
    @Inject
    protected RecyclerView recyclerView;
    @Inject
    protected UserListRecyclerViewAdapter rVAdapter;
    @Inject
    protected MyApplication application;

    private String presenterKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PresentationComponent component = getPresentationComponent();
        component.inject(this);

        if (savedInstanceState == null) {
            component.provideMainPresenter().setView(this);
            presenterKey = application.getKey();
            //noinspection unchecked
            application.registerPresenter(new PresenterWrapper(mvpPresenter));
        } else {
            presenterKey = savedInstanceState.getString(ApplicationConstants.OUT_STATE_KEY);
            //noinspection unchecked
            application.getPresenter(presenterKey).getPresenter()
                    .setView(this);
        }

        recyclerView.setAdapter(rVAdapter);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        List<UserSchema> users = mvpPresenter.getUsers();
        if (users != null && !users.isEmpty()){
            setupRecyclerView(users);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mvpPresenter.registerListener();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(ApplicationConstants.OUT_STATE_KEY, presenterKey);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mvpPresenter.unregisterListener();
    }

    @Override
    protected void onDestroy() {
        application.unregisterPresenter(ApplicationConstants.OUT_STATE_KEY);
        super.onDestroy();
    }

    @Override
    public void setupRecyclerView(List<UserSchema> userSchemas) {
        rVAdapter.setUsers(userSchemas);
        utilWorks.dismissProgressDialog();
    }

    @Override
    public void dataFetchFailed() {
        utilWorks.dismissProgressDialog();
        String failMessage = getString(R.string.data_fetch_failed) + " ("
                + getString(R.string.network_error) + ")";
        utilWorks.showToast(failMessage);
        ((TextView) findViewById(R.id.infoText)).setText(failMessage);
    }

    public void clicked(View view) {
        mvpPresenter.makeRequest();
        utilWorks.showProgressDialog();
    }
}
