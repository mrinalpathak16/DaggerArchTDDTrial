package com.example.daggerarchtddtrial.activities.common;

public class PresenterWrapper<T extends BasePresenter> {
    private T presenter;

    public PresenterWrapper(T presenter) {
        this.presenter = presenter;
    }

    public T getPresenter() {
        return presenter;
    }
}
