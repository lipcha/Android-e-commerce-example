package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.model.repository.UserRepository;
import com.foxkiev.app.presentation.view.SignUpView;
import com.foxkiev.app.utils.validation.Validator;

import javax.inject.Inject;

/**
 * Created by lipcha on 07.05.18.
 */

@InjectViewState
public class SignUpPresenter extends BasePresenter<SignUpView> {

    @Inject
    UserRepository mUserRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
    }

    public void signUp(String firstName, String lastName, String email, String password, String confirmPassword) {
        Validator.create()
                .addNotEmptyField(firstName, FieldId.FIRST_NAME)
                .addNotEmptyField(lastName, FieldId.LAST_NAME)
                .addNotEmptyField(email, FieldId.EMAIL_EMPTY)
                .addEmailField(email, FieldId.EMAIL)
                .addNotEmptyField(password, FieldId.PASSWORD_EMPTY)
                .addPasswordField(password, FieldId.PASSWORD)
                .addNotEmptyField(confirmPassword, FieldId.CONFIRM_PASSWORD_EMPTY)
                .addConfirmPasswordField(password, confirmPassword, FieldId.CONFIRM_PASSWORD)
                .validate(() -> {
                    getViewState().beforeCreateCustomer();
                    getExecutor().execute(mUserRepository.createCustomer(email, firstName, lastName, password),
                            customer -> getViewState().onCreateCustomerSuccess(),
                            throwable -> getViewState().onCreateCustomerFailed(throwable)
                    );
                }, fieldId -> getViewState().showNotValidFieldError(fieldId));
    }

}
