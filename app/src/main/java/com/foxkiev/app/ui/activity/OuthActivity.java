package com.foxkiev.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.Router;
import com.foxkiev.app.presentation.presenter.OuthPresenter;
import com.foxkiev.app.presentation.view.OuthView;
import com.foxkiev.app.ui.fragment.SplashFragment;

public class OuthActivity extends BaseActivity implements OuthView {

    @InjectPresenter
    OuthPresenter outhPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_outh;
    }

    @Override
    protected int getFragmentContainerResId() {
        return R.id.flFragmentContainer;
    }

    @Override
    protected void afterViewCreated(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null)
            replaceTopNavigationFragment(SplashFragment.newInstance());
    }

    @Override
    public Router getRouter() {
        return null;
    }
}
