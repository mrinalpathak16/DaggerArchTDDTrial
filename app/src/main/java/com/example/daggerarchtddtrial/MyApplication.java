package com.example.daggerarchtddtrial;

import android.app.Application;

import com.example.daggerarchtddtrial.activities.common.PresenterWrapper;
import com.example.daggerarchtddtrial.common.di.appleveldi.ApplicationComponent;
import com.example.daggerarchtddtrial.common.di.appleveldi.ApplicationModule;
import com.example.daggerarchtddtrial.common.di.appleveldi.DaggerApplicationComponent;

import java.util.ArrayList;
import java.util.List;


public class MyApplication extends Application {
   private ApplicationComponent appComponent;
   private List<PresenterWrapper> presenters = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }

    public void registerPresenter(PresenterWrapper wrapper){
        presenters.add(wrapper);
    }

    public void unregisterPresenter(String key){
        presenters.remove(Integer.parseInt(key));
    }

    public String getKey(){
        return String.valueOf(presenters.size());
    }

    public PresenterWrapper getPresenter(String key){
        return presenters.get(Integer.parseInt(key));
    }

}
