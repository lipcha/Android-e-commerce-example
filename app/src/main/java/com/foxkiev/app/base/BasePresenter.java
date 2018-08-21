package com.foxkiev.app.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.foxkiev.app.App;
import com.foxkiev.app.base.executors.RxExecutor;

/**
 * Created by lipcha on 20.02.18.
 */

public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

    private RxExecutor mRxExecutor;

    public BasePresenter() {
        mRxExecutor = App.getAppComponent().getRxExecutor();
    }

    public RxExecutor getExecutor(){
        return mRxExecutor;
    }

    protected abstract void onAttachView();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        onAttachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxExecutor.onDestroy();
    }
}
