package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by lipcha on 11.05.18.
 */

public interface SettingsView extends MvpView {

    void onLogoutSuccess();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setVisibleAccountSettings(boolean visible);

    @StateStrategyType(SkipStrategy.class)
    void onLanguageChange(String language);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setCurrentLanguage(String language);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setCurrentCurrency(String currencyCode);

    @StateStrategyType(SkipStrategy.class)
    void onCurrencyChange();
}
