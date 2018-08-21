package com.foxkiev.app.model.storage;

import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.utils.Optional;

import io.reactivex.Observable;

/**
 * Created by lipcha on 16.04.18.
 */

public interface PreferenceStorage {

    void storeGustCartId(final String cartId);

    Observable<Optional<String>> getGuestCartId();

    void clearGuestCart();

    void storeCustomerCartId(final String cartId);

    Observable<Optional<String>> getCustomerCartId();

    void storeAccessToken(String token);

    Observable<Optional<String>> getAccessToken();

    String getAccessTokenString();

    int getListMode();

    Observable<Boolean> storeListMode(int listMode);

    void storeCustomer(Customer customer);

    void clearCustomer();

    Observable<Optional<Customer>> getCustomer();

    Observable<Boolean> storeLanguage(String language);

    Observable<String> getCurrentCurrency();

    Observable<Boolean> storeCurrency(String currencyCode);

    String getCurrentLanguage();
}
