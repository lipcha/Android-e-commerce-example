package com.foxkiev.app.model.repository.impl;

import com.foxkiev.app.model.api.CurrencyApi;
import com.foxkiev.app.model.models.Currency;
import com.foxkiev.app.model.repository.CurrencyRepository;
import com.foxkiev.app.model.storage.PreferenceStorage;

import io.reactivex.Observable;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final CurrencyApi mCurrencyApi;

    public CurrencyRepositoryImpl(CurrencyApi currencyApi) {
        this.mCurrencyApi = currencyApi;
    }

    @Override
    public Observable<Currency> getCurrency() {
        return mCurrencyApi.getCurrency();
    }
}
