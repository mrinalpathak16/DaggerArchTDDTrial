package com.example.daggerarchtddtrial.common.di.presentationleveldi;

import com.example.daggerarchtddtrial.activities.mainactivity.MainActivity;
import com.example.daggerarchtddtrial.activities.mainactivity.MainPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {
    MainPresenter provideMainPresenter();
    void inject(MainActivity mainActivity);
}
