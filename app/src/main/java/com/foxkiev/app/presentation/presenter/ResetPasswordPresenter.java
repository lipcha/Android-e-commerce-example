package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.model.repository.UserRepository;
import com.foxkiev.app.presentation.view.ResetPasswordView;
import com.foxkiev.app.utils.validation.Validator;

import javax.inject.Inject;

/**
 * Created by lipcha on 07.05.18.
 */

@InjectViewState
public class ResetPasswordPresenter extends BasePresenter<ResetPasswordView> {

    @Inject
    UserRepository userRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
    }

    public void resetPassword(final String email){
        Validator.create()
                .addEmailField(email, FieldId.EMAIL)
                .validate(() -> resetPasswordRequest(email), fieldId -> getViewState().onEmailNotValid());
    }

    private void resetPasswordRequest(final String email){
        getViewState().beforeResetPassword();
        getExecutor().execute(userRepository.resetPassword(email), s -> getViewState().onResetPasswordSuccess(), throwable -> getViewState().onResetPasswordFailed(throwable));
    }
}
