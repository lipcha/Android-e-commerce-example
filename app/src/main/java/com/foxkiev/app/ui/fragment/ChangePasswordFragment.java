package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.presentation.presenter.ChangePasswordPresenter;
import com.foxkiev.app.presentation.view.ChangePasswordView;
import com.foxkiev.app.ui.custom_views.dialogs.ConfirmDialog;
import com.foxkiev.app.ui.custom_views.dialogs.ProgressDialogFragment;
import com.foxkiev.app.utils.ErrorHandler;
import com.foxkiev.app.utils.RxUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordFragment extends BaseFragment implements ChangePasswordView{

    @InjectPresenter
    ChangePasswordPresenter mChangePasswordPresenter;

    @BindView(R.id.tilPasswordWrapper)
    TextInputLayout tilPasswordWrapper;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.tilNewPasswordWrapper)
    TextInputLayout tilNewPasswordWrapper;

    @BindView(R.id.etNewPassword)
    EditText etNewPassword;

    @BindView(R.id.tilConfirmPasswordWrapper)
    TextInputLayout tilConfirmPasswordWrapper;

    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;

    public static BaseFragment newInstance(){
        return new ChangePasswordFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_change_password;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RxUtils.interceptTextChange(etPassword, s -> tilPasswordWrapper.setErrorEnabled(false));
        RxUtils.interceptTextChange(etNewPassword, s -> tilNewPasswordWrapper.setErrorEnabled(false));
        RxUtils.interceptTextChange(etConfirmPassword, s -> tilConfirmPasswordWrapper.setErrorEnabled(false));
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_change_password);
    }

    @OnClick(R.id.btnChangePassword)
    void onChangePasswordClick(View v){
        mChangePasswordPresenter.changePassword(
                etPassword.getText().toString(),
                etNewPassword.getText().toString(),
                etConfirmPassword.getText().toString()
        );
    }

    @Override
    public void beforeChangePassword() {
        ProgressDialogFragment.show(getFragmentManager());
    }

    @Override
    public void onChangePasswordSuccess() {
        ProgressDialogFragment.hide(getFragmentManager());
        ConfirmDialog.newBuilder(getBaseActivity())
                .setMessage(getString(R.string.message_password_changed))
                .setHeaderBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_bright))
                .setVisibleCancelButton(false)
                .setIcon(R.drawable.ic_success)
                .setConfirmCallback(() -> getRouter().popBackStack())
                .show();
    }

    @Override
    public void onChangePasswordFailed(Throwable throwable) {
        ProgressDialogFragment.hide(getFragmentManager());
        ErrorHandler.onError(getContext(), throwable);
    }

    @Override
    public void showNotValidFieldError(FieldId fieldId) {
        switch (fieldId){
            case PASSWORD:
                tilPasswordWrapper.setError(getString(R.string.error_not_valid_password));
                break;
            case PASSWORD_EMPTY:
                tilPasswordWrapper.setError(getString(R.string.error_required_field));
                break;
            case NEW_PASSWORD:
                tilNewPasswordWrapper.setError(getString(R.string.error_not_valid_password));
                break;
            case NEW_PASSWORD_EMPTY:
                tilNewPasswordWrapper.setError(getString(R.string.error_required_field));
                break;
            case CONFIRM_PASSWORD:
                tilConfirmPasswordWrapper.setError(getString(R.string.error_enter_this_same_value));
                break;
            case CONFIRM_PASSWORD_EMPTY:
                tilConfirmPasswordWrapper.setError(getString(R.string.error_required_field));
                break;
        }
    }
}
