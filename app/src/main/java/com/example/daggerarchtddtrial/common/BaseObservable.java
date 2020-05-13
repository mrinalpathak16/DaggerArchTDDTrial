package com.example.daggerarchtddtrial.common;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseObservable<LISTENER_CLASS> {

    private List<LISTENER_CLASS> listeners = new ArrayList<>();

    public void registerListener(LISTENER_CLASS listener){
        listeners.add(listener);
    }

    public void unregisterListener(LISTENER_CLASS listener){
        listeners.remove(listener);
    }

    protected List<LISTENER_CLASS> getListeners() {
        return listeners;
    }
}
