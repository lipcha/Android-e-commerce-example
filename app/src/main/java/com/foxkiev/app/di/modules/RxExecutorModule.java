package com.foxkiev.app.di.modules;

import com.foxkiev.app.base.RxExecutorImpl;
import com.foxkiev.app.base.executors.JobExecutor;
import com.foxkiev.app.base.executors.RxExecutor;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by lipcha on 22.02.18.
 */

@Module
public class RxExecutorModule {

    @Provides
    RxExecutor provideRxExecutor(final Executor jobExecutor, final Scheduler scheduler){
        return new RxExecutorImpl(jobExecutor, scheduler);
    }

    @Provides
    @Singleton
    Executor provideThreadExecutor(){
        return new JobExecutor();
    }

    @Provides
    @Singleton
    Scheduler providePostExecutionScheduler(){
        return AndroidSchedulers.mainThread();
    }
}
