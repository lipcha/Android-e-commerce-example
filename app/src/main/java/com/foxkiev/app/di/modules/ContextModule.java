package com.foxkiev.app.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lipcha on 20.02.18.
 */

@Module
public class ContextModule {

    private Context mContext;

    public ContextModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mContext;
    }

}
