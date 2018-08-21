package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by lipcha on 07.05.18.
 */

public interface ResetPasswordView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void beforeResetPassword();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void onResetPasswordSuccess();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void onResetPasswordFailed(final Throwable throwable);

    @StateStrategyType(SkipStrategy.class)
    void onEmailNotValid();
}
