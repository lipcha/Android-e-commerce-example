package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.presentation.presenter.SignUpPresenter;
import com.foxkiev.app.presentation.view.SignUpView;
import com.foxkiev.app.ui.custom_views.dialogs.ConfirmDialog;
import com.foxkiev.app.ui.custom_views.dialogs.ProgressDialogFragment;
import com.foxkiev.app.utils.ErrorHandler;
import com.foxkiev.app.utils.RxUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lipcha on 17.04.18.
 */

public class SignUpFragment extends BaseFragment implements SignUpView {

    @BindView(R.id.tilFirstNameWrapper)
    TextInputLayout tilFirstNameWrapper;

    @BindView(R.id.tilLastNameWrapper)
    TextInputLayout tilLastNameWrapper;

    @BindView(R.id.tilEmailWrapper)
    TextInputLayout tilEmailWrapper;

    @BindView(R.id.tilPasswordWrapper)
    TextInputLayout tilPasswordWrapper;

    @BindView(R.id.tilConfirmPasswordWrapper)
    TextInputLayout tilConfirmPasswordWrapper;

    @BindView(R.id.etFirstName)
    EditText etFirstName;

    @BindView(R.id.etLastName)
    EditText etLastName;

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;

    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    @InjectPresenter
    SignUpPresenter mSignUpPresenter;

    public static BaseFragment newInstance(){
        return new SignUpFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_sign_up;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RxUtils.interceptTextChange(etFirstName, s -> tilFirstNameWrapper.setErrorEnabled(false));
        RxUtils.interceptTextChange(etLastName, s -> tilLastNameWrapper.setErrorEnabled(false));
        RxUtils.interceptTextChange(etEmail, s -> tilEmailWrapper.setErrorEnabled(false));
        RxUtils.interceptTextChange(etPassword, s -> tilPasswordWrapper.setErrorEnabled(false));
        RxUtils.interceptTextChange(etConfirmPassword, s -> tilConfirmPasswordWrapper.setErrorEnabled(false));
    }

    @OnClick(R.id.btnSignUp)
    void onSignUpClick(View v){
        final String firstName = etFirstName.getText().toString();
        final String lastName = etLastName.getText().toString();
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        final String confirmPassword = etConfirmPassword.getText().toString();
        mSignUpPresenter.signUp(firstName, lastName, email, password, confirmPassword);
    }


    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    public void beforeCreateCustomer() {
        ProgressDialogFragment.show(getFragmentManager());
    }

    @Override
    public void onCreateCustomerSuccess() {
        ProgressDialogFragment.hide(getFragmentManager());
        clearFields();
        ConfirmDialog.newBuilder(getBaseActivity())
                .setMessage(getString(R.string.message_account_created))
                .setHeaderBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_bright))
                .setVisibleCancelButton(false)
                .setIcon(R.drawable.ic_success)
                .show();
    }

    @Override
    public void onCreateCustomerFailed(Throwable throwable) {
        ProgressDialogFragment.hide(getFragmentManager());
        ErrorHandler.onError(getContext(), throwable);
    }

    @Override
    public void showNotValidFieldError(FieldId fieldId) {
        switch (fieldId){
            case FIRST_NAME:
                tilFirstNameWrapper.setError(getString(R.string.error_required_field));
                break;
            case LAST_NAME:
                tilLastNameWrapper.setError(getString(R.string.error_required_field));
                break;
            case EMAIL:
                tilEmailWrapper.setError(getString(R.string.error_enter_valid_email));
                break;
            case EMAIL_EMPTY:
                tilEmailWrapper.setError(getString(R.string.error_required_field));
                break;
            case PASSWORD:
                tilPasswordWrapper.setError(getString(R.string.error_not_valid_password));
                break;
            case PASSWORD_EMPTY:
                tilPasswordWrapper.setError(getString(R.string.error_required_field));
                break;
            case CONFIRM_PASSWORD:
                tilConfirmPasswordWrapper.setError(getString(R.string.error_enter_this_same_value));
                break;
            case CONFIRM_PASSWORD_EMPTY:
                tilConfirmPasswordWrapper.setError(getString(R.string.error_required_field));
                break;
        }
    }

    private void clearFields(){
        final EditText[] views = {etFirstName, etLastName, etEmail, etPassword, etConfirmPassword};
        ButterKnife.apply(views, (view, index) -> view.setText(""));
    }
}
