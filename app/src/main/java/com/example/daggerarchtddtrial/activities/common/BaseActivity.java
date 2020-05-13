package com.example.daggerarchtddtrial.activities.common;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daggerarchtddtrial.MyApplication;
import com.example.daggerarchtddtrial.common.di.appleveldi.ApplicationComponent;
import com.example.daggerarchtddtrial.common.di.presentationleveldi.PresentationComponent;
import com.example.daggerarchtddtrial.common.di.presentationleveldi.PresentationModule;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements BaseView<T>{

    protected T mvpPresenter;
    private boolean isUsed = false;

    @Override
    public void setPresenter(@NonNull T presenter){
        mvpPresenter = presenter;
    }

    @UiThread
    public PresentationComponent getPresentationComponent(){
        if (isUsed){
            throw new RuntimeException("One Component is already in use!");
        }
        isUsed = true;
        return getApplicationComponent().getPresentationComponent(
                new PresentationModule(this));
    }

    private ApplicationComponent getApplicationComponent(){
        return ((MyApplication)getApplication()).getAppComponent();
    }
}
