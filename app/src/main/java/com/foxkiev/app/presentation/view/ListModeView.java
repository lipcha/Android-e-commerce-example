package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by lipcha on 01.03.18.
 */

public interface ListModeView extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void changeListMode(int mode, int currentPosition);

}
