package com.example.daggerarchtddtrial.activities.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.daggerarchtddtrial.networking.FetchAllUsersRequest;
import com.example.daggerarchtddtrial.networking.UserSchema;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter, FetchAllUsersRequest.Listener {
    private MainContract.View mvpView;
    private FetchAllUsersRequest usersRequest;
    private List<UserSchema> userSchemas;

    @Override
    public void setView(@NonNull MainContract.View mvpView) {
        if (this.mvpView==mvpView) {
            throw new RuntimeException("View Injected more than once!");
        }
        this.mvpView = mvpView;
        this.mvpView.setPresenter(this);
    }

    @Inject
    public MainPresenter(FetchAllUsersRequest usersRequest) {
        this.usersRequest = usersRequest;
    }

    @Override
    public void makeRequest() {
        usersRequest.fetchUsersAndNotify();
    }

    @Override
    public void onFetchAllUsersSuccess(List<UserSchema> users) {
        userSchemas = users;
        mvpView.setupRecyclerView(users);
    }

    @Override
    public void onFetchAllUsersFailed() {
        mvpView.dataFetchFailed();
    }

    @Override
    public void registerListener() {
        usersRequest.registerListener(this);
    }

    @Override
    public void unregisterListener() {
        usersRequest.unregisterListener(this);
    }

    @Nullable
    @Override
    public List<UserSchema> getUsers() {
        return userSchemas;
    }
}
