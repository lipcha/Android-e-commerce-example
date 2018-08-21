package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.Constants;
import com.foxkiev.app.base.BasePresenter;
import com.foxkiev.app.model.repository.UserRepository;
import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.presentation.view.SettingsView;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by lipcha on 11.05.18.
 */

@InjectViewState
public class SettingsPresenter extends BasePresenter<SettingsView>{

    @Inject
    UserRepository mUserRepository;

    @Inject
    PreferenceStorage mPreferenceStorage;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        getExecutor().execute(mUserRepository.isAuthorized(), authorized -> getViewState().setVisibleAccountSettings(authorized));
        getExecutor().execute(Observable.fromCallable(() -> mPreferenceStorage.getCurrentLanguage()), language -> getViewState().setCurrentLanguage(language));
        getExecutor().execute(mPreferenceStorage.getCurrentCurrency(), currentCurrency -> getViewState().setCurrentCurrency(currentCurrency));

    }

    public void changeLanguage(final String language){
        getExecutor().execute(
                Observable.fromCallable(() -> mPreferenceStorage.getCurrentLanguage())
                        .filter(currentLanguage -> !currentLanguage.equals(language))
                        .flatMap(s -> mPreferenceStorage.storeLanguage(language)),
                aBoolean -> {
                    getViewState().setCurrentLanguage(language);
                    getViewState().onLanguageChange(language);
                });
    }

    public void changeCurrency(final String newCurrency){
        getExecutor().execute(
                mPreferenceStorage.getCurrentCurrency()
                        .filter(currentCurrency -> !newCurrency.equals(currentCurrency))
                        .flatMap(c -> mPreferenceStorage.storeCurrency(newCurrency)),
                currentCurrency -> {
                    getViewState().setCurrentCurrency(newCurrency);
                    getViewState().onCurrencyChange();
                });
    }

    public void logOut(){
        getExecutor().execute(mUserRepository.logout(), o -> getViewState().onLogoutSuccess());
    }
}
