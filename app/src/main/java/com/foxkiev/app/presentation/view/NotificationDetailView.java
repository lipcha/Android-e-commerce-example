package com.foxkiev.app.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.foxkiev.app.base.list.MvpListView;
import com.foxkiev.app.model.models.NotificationDetail;

public interface NotificationDetailView extends MvpListView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void renderNotificationHeader(NotificationDetail notificationDetail);
}
