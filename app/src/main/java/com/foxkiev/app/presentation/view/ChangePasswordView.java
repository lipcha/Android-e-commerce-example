package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.model.models.FieldId;

public interface ChangePasswordView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void beforeChangePassword();

    @StateStrategyType(SkipStrategy.class)
    void onChangePasswordSuccess();

    @StateStrategyType(SkipStrategy.class)
    void onChangePasswordFailed(Throwable throwable);

    @StateStrategyType(SkipStrategy.class)
    void showNotValidFieldError(FieldId fieldId);
}
