package com.foxkiev.app.model.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.foxkiev.app.Constants;
import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.utils.Optional;
import com.google.gson.Gson;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by lipcha on 16.04.18.
 */

public class PreferenceStorageImpl implements PreferenceStorage{

    private static final String PREFERENCE_NAME = "app_shared_preference";
    private static final String KEY_GUEST_CART_ID = "guest_cart_id";
    private static final String KEY_CUSTOMER_CART_ID = "customer_cart_id";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_USER = "user";
    private static final String KEY_CURRENT_LANGUAGE = "language";
    private static final String KEY_CURRENT_CURRENCY = "currency";
    private static final String KEY_LIST_MODE = "list_mode";

    private final SharedPreferences mPreferences;

    public PreferenceStorageImpl(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }


    @SuppressLint("ApplySharedPref")
    @Override
    public void storeGustCartId(String cartId) {
        mPreferences.edit().putString(KEY_GUEST_CART_ID, cartId).commit();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void clearGuestCart(){
        mPreferences.edit().remove(KEY_GUEST_CART_ID).commit();
    }

    @Override
    public Observable<Optional<String>> getGuestCartId() {
        return Observable.create(e -> {
            final String guestCartId = mPreferences.getString(KEY_GUEST_CART_ID, null);
            e.onNext(Optional.ofEmptyString(guestCartId));
            e.onComplete();
        });
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void storeCustomerCartId(String cartId) {
        mPreferences.edit().putString(KEY_CUSTOMER_CART_ID, cartId).commit();
    }

    @Override
    public Observable<Optional<String>> getCustomerCartId() {
        return Observable.create(e -> {
            final String guestCartId = mPreferences.getString(KEY_CUSTOMER_CART_ID, null);
            e.onNext(Optional.ofEmptyString(guestCartId));
            e.onComplete();
        });
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void storeAccessToken(String token) {
        mPreferences.edit().putString(KEY_ACCESS_TOKEN, token).commit();
    }

    @Override
    public Observable<Optional<String>> getAccessToken() {
        return Observable.create(e -> {
            final String accessToken = mPreferences.getString(KEY_ACCESS_TOKEN, "");
            e.onNext(Optional.ofEmptyString(accessToken));
            e.onComplete();
        });
    }

    @Override
    public String getAccessTokenString() {
        return mPreferences.getString(KEY_ACCESS_TOKEN, "");
    }

    @Override
    public int getListMode() {
        return mPreferences.getInt(KEY_LIST_MODE, Constants.MODE_LIST);
    }

    @Override
    public Observable<Boolean> storeListMode(int listMode) {
        return Observable.create(e -> {
            e.onNext(mPreferences.edit().putInt(KEY_LIST_MODE, listMode).commit());
            e.onComplete();
        });
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void storeCustomer(Customer user) {
        mPreferences.edit().putString(KEY_USER, new Gson().toJson(user)).commit();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void clearCustomer() {
        mPreferences.edit().putString(KEY_USER, "").commit();
    }

    @Override
    public Observable<Optional<Customer>> getCustomer() {
        return Observable.create(e -> {
            final String userJson = mPreferences.getString(KEY_USER, "");

            e.onNext(TextUtils.isEmpty(userJson) ? Optional.empty() : Optional.of(new Gson().fromJson(userJson, Customer.class)));
            e.onComplete();
        });
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public Observable<Boolean> storeLanguage(String language) {
        return Observable.fromCallable(() -> mPreferences.edit().putString(KEY_CURRENT_LANGUAGE, language).commit());
    }

    @Override
    public String getCurrentLanguage() {
        return mPreferences.getString(KEY_CURRENT_LANGUAGE, Constants.LANGUAGE_DEFAULT);
    }

    @Override
    public Observable<String> getCurrentCurrency() {
        return Observable.create(e -> {
            final String currency = mPreferences.getString(KEY_CURRENT_CURRENCY, Constants.CURRENCY_CODE_UAH);
            e.onNext(currency);
            e.onComplete();
        });
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public Observable<Boolean> storeCurrency(String currencyCode) {
        return Observable.fromCallable(() -> mPreferences.edit().putString(KEY_CURRENT_CURRENCY, currencyCode).commit());
    }
}
