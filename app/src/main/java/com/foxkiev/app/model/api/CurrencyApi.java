package com.foxkiev.app.model.api;


import com.foxkiev.app.model.models.Currency;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CurrencyApi {

    @GET("directory/currency")
    Observable<Currency> getCurrency();
}
