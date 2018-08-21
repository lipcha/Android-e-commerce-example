package com.foxkiev.app.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.presentation.presenter.SplashPresenter;
import com.foxkiev.app.presentation.view.SplashView;
import com.foxkiev.app.ui.activity.MainActivity;

/**
 * Created by lipcha on 22.02.18.
 */

public class SplashFragment extends BaseFragment implements SplashView {

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    public static SplashFragment newInstance(){
        return new SplashFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    public void openMain() {
        startActivity(new Intent(getContext(), MainActivity.class));
        final Activity activity = getActivity();
        if (activity != null)
            activity.finish();
    }
}
