package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.Constants;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.presentation.view.SplashView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by lipcha on 22.02.18.
 */

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {

    @Override
    protected void onAttachView() {
        getExecutor().execute(Observable.timer(Constants.SPLASH_DELAY, TimeUnit.SECONDS), aLong -> {
//            getViewState().openLogin();
            getViewState().openMain();
        });
    }
}
