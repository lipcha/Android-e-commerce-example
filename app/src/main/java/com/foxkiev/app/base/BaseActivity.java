package com.foxkiev.app.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.foxkiev.app.model.storage.PreferenceStorageImpl;
import com.foxkiev.app.utils.LocaleManager;
import com.foxkiev.app.utils.RxUtils;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by lipcha on 20.02.18.
 */

public abstract class BaseActivity extends MvpAppCompatActivity {

    protected abstract int getLayoutResId();
    protected abstract int getFragmentContainerResId();
    protected abstract void afterViewCreated(@Nullable Bundle savedInstanceState);

    public abstract Router getRouter();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        init();
        afterViewCreated(savedInstanceState);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.setLocale(
                newBase,
                new PreferenceStorageImpl(newBase).getCurrentLanguage())
        );
    }

    protected void init(){
        ButterKnife.bind(this);
    }

    public Disposable setupClick(final View view, final Consumer<Object> onNext){
        return RxUtils.setupClick(view, onNext);
    }

    public void addFragmentToBackStack(BaseFragment fragment) {
        try {
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction
                    .replace(getFragmentContainerResId(), fragment)
                    .addToBackStack(fragment.getClass().getName() + System.currentTimeMillis())
                    .commit();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void replaceTopNavigationFragment(BaseFragment fragment) {
        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .replace(getFragmentContainerResId(), fragment)
                .commit();
    }
}
