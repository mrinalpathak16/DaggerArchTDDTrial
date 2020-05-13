package com.example.daggerarchtddtrial.common.di.appleveldi;

import com.example.daggerarchtddtrial.common.ApplicationConstants;
import com.example.daggerarchtddtrial.networking.NetworkApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class NetworkModule {

    @Singleton
    @Provides
    Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(ApplicationConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    NetworkApi getNetworkApi(Retrofit retrofit){
        return retrofit.create(NetworkApi.class);
    }
}
