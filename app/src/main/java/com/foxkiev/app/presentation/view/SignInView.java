package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.model.models.Customer;
import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 22.02.18.
 */

public interface SignInView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void beforeSignIn();

    @StateStrategyType(SkipStrategy.class)
    void onSignInSuccess(Customer customer);

    @StateStrategyType(SkipStrategy.class)
    void onSignInFailed(final Throwable throwable);

    @StateStrategyType(SkipStrategy.class)
    void showNotValidFieldError(FieldId fieldId);
}
