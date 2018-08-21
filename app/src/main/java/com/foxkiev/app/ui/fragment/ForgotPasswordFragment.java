package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.presentation.presenter.ResetPasswordPresenter;
import com.foxkiev.app.presentation.view.ResetPasswordView;
import com.foxkiev.app.ui.custom_views.dialogs.ProgressDialogFragment;
import com.foxkiev.app.utils.ErrorHandler;
import com.foxkiev.app.utils.RxUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lipcha on 04.05.18.
 */

public class ForgotPasswordFragment extends BaseFragment implements ResetPasswordView {

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.tilEmailWrapper)
    TextInputLayout tilEmailWrapper;

    @InjectPresenter
    ResetPasswordPresenter mResetPasswordPresenter;

    public static BaseFragment newInstance(){
        return new ForgotPasswordFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_forgot_password;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RxUtils.interceptTextChange(etEmail, s -> tilEmailWrapper.setErrorEnabled(false));
    }

    @OnClick(R.id.btnResetPassword)
    void onClickResetPassword(View v){
        mResetPasswordPresenter.resetPassword(etEmail.getText().toString());
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_forgot_password);
    }

    @Override
    public void beforeResetPassword() {
        ProgressDialogFragment.show(getFragmentManager());
    }

    @Override
    public void onResetPasswordSuccess() {
        ProgressDialogFragment.hide(getFragmentManager());
    }

    @Override
    public void onResetPasswordFailed(Throwable throwable) {
        ProgressDialogFragment.hide(getFragmentManager());
        ErrorHandler.onError(getContext(), throwable);
    }

    @Override
    public void onEmailNotValid() {
        tilEmailWrapper.setError(getString(R.string.error_enter_valid_email));
    }
}
