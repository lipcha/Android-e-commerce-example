package com.foxkiev.app.model.repository.impl;

import com.foxkiev.app.model.api.UserApi;
import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.model.models.request.ChangePasswordWrapper;
import com.foxkiev.app.model.models.request.CreateCustomerWrapper;
import com.foxkiev.app.model.models.request.Credentials;
import com.foxkiev.app.model.models.request.EmailWrapper;
import com.foxkiev.app.model.repository.UserRepository;
import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.utils.Optional;

import io.reactivex.Observable;
/**
 * Created by lipcha on 22.02.18.
 */

public class UserRepositoryImpl implements UserRepository {

    private final UserApi mUserApi;
    private final PreferenceStorage mPreferenceStorage;

    public UserRepositoryImpl(UserApi userApi, PreferenceStorage preferenceStorage) {
        this.mUserApi = userApi;
        this.mPreferenceStorage = preferenceStorage;
    }

    @Override
    public Observable<Customer> signIn(String email, String password) {
        return mUserApi.signIn(new Credentials(email, password))
                .map(accessToken -> {
                    mPreferenceStorage.storeAccessToken(accessToken);
                    return accessToken;
                })
                .flatMap(s -> getAndStoreCustomer());
    }

    private Observable<Customer> getAndStoreCustomer(){
        return mUserApi.getCustomer()
                .map(customer -> {
                    mPreferenceStorage.storeCustomer(customer);
                    return customer;
                });
    }

    @Override
    public Observable<Customer> createCustomer(String email, String firstName, String lastName, String password) {
        return mUserApi.signUp(new CreateCustomerWrapper(email, firstName, lastName, password));
    }

    @Override
    public Observable<String> changePassword(String password, String newPassword) {
        return mUserApi.changePassword(new ChangePasswordWrapper(password,newPassword));
    }

    @Override
    public Observable<Boolean> isAuthorized() {
        return mPreferenceStorage.getAccessToken()
                .map(Optional::isPresent);
    }

    @Override
    public Observable<Optional<Customer>> getCustomerMy() {
        return mPreferenceStorage.getCustomer();
    }

    @Override
    public Observable<Boolean> logout() {
        return Observable.create(e -> {
            mPreferenceStorage.storeAccessToken("");
            mPreferenceStorage.clearCustomer();
            e.onNext(true);
            e.onComplete();
        });
    }

    @Override
    public Observable<String> resetPassword(String email) {
        return mUserApi.resetPassword(new EmailWrapper(email));
    }
}
