package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;

public class EditAccountFragment extends BaseFragment{

    public static BaseFragment newInstance(){
        return new EditAccountFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_edit_account;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_edit_account);
    }
}
