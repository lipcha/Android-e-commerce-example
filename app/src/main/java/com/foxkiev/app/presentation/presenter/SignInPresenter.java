package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.models.FieldId;
import com.foxkiev.app.model.repository.CartRepository;
import com.foxkiev.app.model.repository.UserRepository;
import com.foxkiev.app.presentation.view.SignInView;
import com.foxkiev.app.utils.validation.Validator;

import javax.inject.Inject;

/**
 * Created by lipcha on 22.02.18.
 */

@InjectViewState
public class SignInPresenter extends BasePresenter<SignInView> {

    @Inject
    UserRepository mUserRepository;

    @Inject
    CartRepository mCartRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
    }

    public void signIn(final String email, final String password){
        Validator.create()
                .addEmailField(email, FieldId.EMAIL)
                .addPasswordField(password, FieldId.PASSWORD)
                .validate(
                        () -> signInRequest(email, password),
                        fieldId -> getViewState().showNotValidFieldError(fieldId)
                );

    }

    private void signInRequest(final String email, final String password){
        getViewState().beforeSignIn();
        getExecutor().execute(
                mUserRepository.signIn(email, password)
                        .flatMap(customer -> mCartRepository.transferGuestCart()
                                .map(aBoolean -> customer)),
                customer -> getViewState().onSignInSuccess(customer), throwable -> getViewState().onSignInFailed(throwable));
    }
}
