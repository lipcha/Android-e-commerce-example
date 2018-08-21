package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.model.models.Customer;

/**
 * Created by lipcha on 11.05.18.
 */

public interface ProfileView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void renderCustomer(Customer customer);

    @StateStrategyType(SkipStrategy.class)
    void onGetCustomerFailed();
}
