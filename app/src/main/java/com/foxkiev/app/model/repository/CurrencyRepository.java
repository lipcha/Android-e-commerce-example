package com.foxkiev.app.model.repository;

import com.foxkiev.app.model.models.Currency;
import io.reactivex.Observable;

public interface CurrencyRepository {

    Observable<Currency> getCurrency();
}
