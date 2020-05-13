package com.example.daggerarchtddtrial.networking;

import androidx.annotation.Nullable;

import com.example.daggerarchtddtrial.common.BaseObservable;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchAllUsersRequest extends BaseObservable<FetchAllUsersRequest.Listener> {

    public interface Listener {
        void onFetchAllUsersSuccess(List<UserSchema> users);

        void onFetchAllUsersFailed();
    }

    private final NetworkApi networkApi;

    @Nullable
    private Call<List<UserSchema>> call;

    @Inject
    public FetchAllUsersRequest(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void fetchUsersAndNotify() {
        cancelCurrent();

        call = networkApi.getAllUsers();
        call.enqueue(new Callback<List<UserSchema>>() {
            @Override
            public void onResponse(@NotNull Call<List<UserSchema>> call,
                                   @NotNull Response<List<UserSchema>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<UserSchema>> call, @NotNull Throwable t) {
                notifyFailure();
            }
        });
    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onFetchAllUsersFailed();
        }
    }

    private void notifySuccess(List<UserSchema> userSchemas) {
        List<UserSchema> unmodifiableUsers = Collections.unmodifiableList(userSchemas);
        for (Listener listener : getListeners()) {
            listener.onFetchAllUsersSuccess(unmodifiableUsers);
        }
    }

    private void cancelCurrent() {
        if (call != null && !call.isCanceled() && !call.isExecuted()) {
            call.cancel();
        }
    }

}
