package com.example.daggerarchtddtrial.networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("posts")
    Call<List<UserSchema>> getAllUsers();
}
