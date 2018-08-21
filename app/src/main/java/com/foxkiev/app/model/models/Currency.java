package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lipcha on 10.04.18.
 */

public class Currency {

    @SerializedName("base_currency_code")
    private String baseCurrencyCode;

    @SerializedName("base_currency_symbol")
    private String baseCurrencySymbol;

    @SerializedName("default_display_currency_code")
    private String defaultDisplayCurrencyCode;

    @SerializedName("default_display_currency_symbol")
    private String defaultDisplayCurrencySymbol;

    @SerializedName("exchange_rates")
    private List<Rate> exchangeRates;

    public String getBaseCurrencyCode() {
        return baseCurrencyCode;
    }

    public String getBaseCurrencySymbol() {
        return baseCurrencySymbol;
    }

    public String getDefaultDisplayCurrencyCode() {
        return defaultDisplayCurrencyCode;
    }

    public String getDefaultDisplayCurrencySymbol() {
        return defaultDisplayCurrencySymbol;
    }

    public List<Rate> getExchangeRates() {
        return exchangeRates;
    }

    public static class Rate{
        @SerializedName("currency_to")
        private String currencyTo;

        private float rate;

        public String getCurrencyTo() {
            return currencyTo;
        }

        public float getRate() {
            return rate;
        }
    }


}
