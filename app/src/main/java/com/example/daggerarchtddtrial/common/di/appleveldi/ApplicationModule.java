package com.example.daggerarchtddtrial.common.di.appleveldi;

import com.example.daggerarchtddtrial.MyApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private MyApplication application;

    public ApplicationModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    MyApplication provideMyApplication(){
        return application;
    }

}
