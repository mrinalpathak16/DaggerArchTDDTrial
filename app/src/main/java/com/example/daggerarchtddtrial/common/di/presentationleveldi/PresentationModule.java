package com.example.daggerarchtddtrial.common.di.presentationleveldi;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerarchtddtrial.R;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {
    private AppCompatActivity activity;

    public PresentationModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    Context getContext(){
        return activity;
    }

    @Provides
    RecyclerView provideRecyclerView(Context context){
        RecyclerView  rV = activity.findViewById(R.id.recyclerView);
        rV.setLayoutManager(new LinearLayoutManager(context));
        return rV;
    }

}
