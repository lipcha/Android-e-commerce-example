package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.model.models.Notification;
import com.foxkiev.app.model.repository.NotificationRepository;
import com.foxkiev.app.presentation.view.NotificationListView;

import javax.inject.Inject;

/**
 * Created by lipcha on 20.04.18.
 */

@InjectViewState
public class NotificationListPresenter extends BaseListPresenter<Notification, NotificationListView>{

    @Inject
    NotificationRepository mNotificationRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        super.onAttachView();
    }

    @Override
    public void refreshList() {
        getExecutor().execute(mNotificationRepository.getNotificationList(0, 40), notificationListWrapper -> {
            onLoadListSuccess(notificationListWrapper.getNotifications());
        }, this::onLoadListFailed);
    }
}
