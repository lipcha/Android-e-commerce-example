package com.foxkiev.app.model.models;


import com.foxkiev.app.Constants;

public class DefaultCurrency {

    private String currencyKey;
    private String currencyName;
    private float rate = 1;

    public DefaultCurrency(String currencyKey, Currency currency) {
        this.currencyKey = currencyKey;
        if (currencyKey.equals(Constants.CURRENCY_CODE_EUR)){
            currencyName = currency.getBaseCurrencySymbol();
        }else currencyName = currency.getDefaultDisplayCurrencySymbol();

        for (Currency.Rate rate : currency.getExchangeRates()){
            if (rate.getCurrencyTo().equals(currencyKey)){
                this.rate = rate.getRate();
            }
        }
    }

    public String getCurrencyKey() {
        return currencyKey;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public float getRate() {
        return rate;
    }
}
