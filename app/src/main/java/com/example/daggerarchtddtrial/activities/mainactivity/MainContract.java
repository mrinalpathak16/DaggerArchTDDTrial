package com.example.daggerarchtddtrial.activities.mainactivity;

import com.example.daggerarchtddtrial.activities.common.BasePresenter;
import com.example.daggerarchtddtrial.activities.common.BaseView;
import com.example.daggerarchtddtrial.networking.UserSchema;

import java.util.List;

class MainContract {

    public interface View extends BaseView<Presenter> {

        void setupRecyclerView(List<UserSchema> userSchemas);

        void dataFetchFailed();
    }

    public interface Presenter extends BasePresenter<View> {

        void makeRequest();

        void registerListener();

        void unregisterListener();

        List<UserSchema> getUsers();
    }

}
