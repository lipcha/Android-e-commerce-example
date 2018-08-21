package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by lipcha on 23.02.18.
 */

public interface MainActivityView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void changeTab(int tab);
}
