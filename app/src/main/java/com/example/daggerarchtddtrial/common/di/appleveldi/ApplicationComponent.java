package com.example.daggerarchtddtrial.common.di.appleveldi;

import com.example.daggerarchtddtrial.common.di.presentationleveldi.PresentationComponent;
import com.example.daggerarchtddtrial.common.di.presentationleveldi.PresentationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class})
public interface ApplicationComponent {
    PresentationComponent getPresentationComponent(PresentationModule presentationModule);
}
