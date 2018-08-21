package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.repository.UserRepository;
import com.foxkiev.app.presentation.view.MainActivityView;

import javax.inject.Inject;

/**
 * Created by lipcha on 23.02.18.
 */

@InjectViewState
public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    @Inject
    UserRepository mUserRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
    }

    public void setTab(final int tabIndex){
        if (tabIndex == 4){
            getExecutor().execute(mUserRepository.isAuthorized(), authorized -> getViewState().changeTab(authorized ? 5 : 4));
        }else
            getViewState().changeTab(tabIndex);
    }
}
