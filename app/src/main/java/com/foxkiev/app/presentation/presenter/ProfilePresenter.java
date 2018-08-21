package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.repository.UserRepository;
import com.foxkiev.app.presentation.view.ProfileView;

import javax.inject.Inject;

/**
 * Created by lipcha on 11.05.18.
 */

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView> {

    @Inject
    UserRepository mUserRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
    }

    public void updateCustomer(){
        getExecutor().execute(mUserRepository.getCustomerMy(), optionalCustomer -> {
            getViewState().renderCustomer(optionalCustomer.get());
        }, throwable -> getViewState().onGetCustomerFailed());
    }
}
