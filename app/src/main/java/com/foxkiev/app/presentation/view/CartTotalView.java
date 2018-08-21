package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by lipcha on 23.04.18.
 */

public interface CartTotalView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setCartItemsCount(int count);
}
