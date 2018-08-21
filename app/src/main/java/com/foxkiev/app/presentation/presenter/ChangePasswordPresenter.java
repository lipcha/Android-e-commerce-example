package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.model.repository.UserRepository;
import com.foxkiev.app.presentation.view.ChangePasswordView;
import com.foxkiev.app.utils.validation.Validator;

import javax.inject.Inject;


@InjectViewState
public class ChangePasswordPresenter extends BasePresenter<ChangePasswordView>{

    @Inject
    UserRepository mUserRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
    }

    public void changePassword(final String currentPassword, final String newPassword, final String confirmNewPassword){
        Validator.create()
                .addNotEmptyField(currentPassword, FieldId.PASSWORD_EMPTY)
                .addPasswordField(currentPassword, FieldId.PASSWORD)
                .addNotEmptyField(newPassword, FieldId.NEW_PASSWORD_EMPTY)
                .addPasswordField(newPassword, FieldId.NEW_PASSWORD)
                .addNotEmptyField(confirmNewPassword, FieldId.CONFIRM_PASSWORD_EMPTY)
                .addConfirmPasswordField(newPassword, confirmNewPassword, FieldId.CONFIRM_PASSWORD)
                .validate(() -> {
                    getViewState().beforeChangePassword();
                    getExecutor().execute(mUserRepository.changePassword(currentPassword, newPassword),
                            customer -> getViewState().onChangePasswordSuccess(),
                            throwable -> getViewState().onChangePasswordFailed(throwable)
                    );
                }, fieldId -> getViewState().showNotValidFieldError(fieldId));
    }
}
