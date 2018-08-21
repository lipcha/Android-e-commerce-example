package com.foxkiev.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.stetho.Stetho;
import com.foxkiev.app.di.components.AppComponent;
import com.foxkiev.app.di.components.DaggerAppComponent;
import com.foxkiev.app.di.components.DaggerRepositoryComponent;
import com.foxkiev.app.di.components.RepositoryComponent;
import com.foxkiev.app.di.modules.ContextModule;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by lipcha on 20.02.18.
 */

public class App extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        buildAppComponent();
        Stetho.initializeWithDefaults(this);
        FirebaseMessaging.getInstance().subscribeToTopic("all");

    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    private void buildAppComponent(){
        sAppComponent = DaggerAppComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static RepositoryComponent getRepositoryComponent(){
        return DaggerRepositoryComponent
                .builder()
                .appComponent(getAppComponent())
                .build();
    }
}
