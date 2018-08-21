package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.model.models.Currency;
import com.foxkiev.app.model.models.DefaultCurrency;

public interface CurrencyView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setCurrency(DefaultCurrency currency);
}
