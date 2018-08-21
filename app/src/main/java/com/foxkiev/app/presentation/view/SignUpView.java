package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 07.05.18.
 */

public interface SignUpView extends MvpView{

    @StateStrategyType(SkipStrategy.class)
    void beforeCreateCustomer();

    @StateStrategyType(SkipStrategy.class)
    void onCreateCustomerSuccess();

    @StateStrategyType(SkipStrategy.class)
    void onCreateCustomerFailed(Throwable throwable);

    @StateStrategyType(SkipStrategy.class)
    void showNotValidFieldError(FieldId fieldId);
}
