package com.foxkiev.app.di.modules;

import android.content.Context;

import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.model.storage.PreferenceStorageImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lipcha on 16.04.18.
 */

@Module
public class StorageModule {

    @Provides
    @Singleton
    public PreferenceStorage providePreferenceStorage(final Context context){
        return new PreferenceStorageImpl(context);
    }
}
