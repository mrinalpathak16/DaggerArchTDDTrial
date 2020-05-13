package com.example.daggerarchtddtrial.activities.common;

public interface BasePresenter<T extends BaseView> {
    void setView(T mvpView);
}
