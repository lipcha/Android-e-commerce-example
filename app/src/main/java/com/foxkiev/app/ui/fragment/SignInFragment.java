package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.presentation.presenter.SignInPresenter;
import com.foxkiev.app.presentation.view.SignInView;
import com.foxkiev.app.ui.activity.MainActivity;
import com.foxkiev.app.ui.custom_views.dialogs.ProgressDialogFragment;
import com.foxkiev.app.utils.ErrorHandler;
import com.foxkiev.app.utils.RxUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lipcha on 22.02.18.
 */

public class SignInFragment extends BaseFragment implements SignInView {

    private static final String KEY_CHECKOUT_AFTER = "key_checkout_after";

    @InjectPresenter
    SignInPresenter mSignInPresenter;

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.tilEmailWrapper)
    TextInputLayout tilEmailWrapper;

    @BindView(R.id.tilPasswordWrapper)
    TextInputLayout tilPasswordWrapper;

    public static BaseFragment newInstance(final boolean checkoutAfter){
        final BaseFragment fragment = new SignInFragment();
        final Bundle args = new Bundle();
        args.putBoolean(KEY_CHECKOUT_AFTER, checkoutAfter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RxUtils.interceptTextChange(etEmail, s -> clearErrors());
        RxUtils.interceptTextChange(etPassword,  s -> clearErrors());
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @OnClick(R.id.btnSignIn)
    public void onSignInClicked(View view){
        mSignInPresenter.signIn(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @OnClick(R.id.btnForgotPassword)
    public void onForgotPasswordClicked(View view){
        getRouter().addFragmentToBackStack(ForgotPasswordFragment.newInstance());
    }

    @Override
    public void beforeSignIn() {
        ProgressDialogFragment.show(getBaseActivity().getSupportFragmentManager());
    }

    @Override
    public void onSignInSuccess(Customer customer) {
        ProgressDialogFragment.hide(getBaseActivity().getSupportFragmentManager());
        final BaseActivity baseActivity = getBaseActivity();
        getRouter().popBackStack();
        if (getArguments() != null && getArguments().getBoolean(KEY_CHECKOUT_AFTER)){
            getRouter().addFragmentToBackStack(ShippingAddressFragment.newInstance());
            return;
        }
        if (baseActivity != null && baseActivity instanceof MainActivity){
            ((MainActivity) baseActivity).setTab(4);
            ((MainActivity) baseActivity).updateCartItemsCount();
        }
    }

    @Override
    public void onSignInFailed(Throwable throwable) {
        ProgressDialogFragment.hide(getBaseActivity().getSupportFragmentManager());
        ErrorHandler.onError(getContext(), throwable);
    }

    @Override
    public void showNotValidFieldError(FieldId fieldId) {
        switch (fieldId){
            case EMAIL:
                tilEmailWrapper.setError(getString(R.string.error_enter_valid_email));
                break;
            case PASSWORD:
                tilPasswordWrapper.setError(getString(R.string.error_required_field));
                break;
        }
    }

    private void clearErrors(){
        tilEmailWrapper.setErrorEnabled(false);
        tilPasswordWrapper.setErrorEnabled(false);
    }
}
