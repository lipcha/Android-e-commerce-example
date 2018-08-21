package com.foxkiev.app.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.foxkiev.app.utils.RxUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * Created by lipcha on 20.02.18.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

    private Unbinder mUnbinder;

    protected abstract int getLayoutResId();
    protected abstract void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState);
    protected abstract String getTitle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(getLayoutResId(), container, false);
        init(rootView);
        return rootView;
    }

    protected void init(final View rootView){
        mUnbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        afterViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    protected BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }

    public Router getRouter(){
        return getBaseActivity().getRouter();
    }

    public void setupClick(final View view, final Consumer<Object> onNext){
        RxUtils.setupClick(view, onNext);
    }

    public void invalidateOptionsMenu(){
        if (getActivity() != null)
            getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onResume() {
        super.onResume();
        final String title = getTitle();
        if (!TextUtils.isEmpty(title))
            getBaseActivity().setTitle(title);
    }
}
