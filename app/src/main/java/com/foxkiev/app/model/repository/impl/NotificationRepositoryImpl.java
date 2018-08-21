package com.foxkiev.app.model.repository.impl;

import com.foxkiev.app.model.api.NotificationApi;
import com.foxkiev.app.model.models.NotificationDetail;
import com.foxkiev.app.model.models.NotificationListWrapper;
import com.foxkiev.app.model.repository.NotificationRepository;

import io.reactivex.Observable;

public class NotificationRepositoryImpl implements NotificationRepository {

    private NotificationApi mNotificationApi;

    public NotificationRepositoryImpl(NotificationApi NotificationApi) {
        this.mNotificationApi = NotificationApi;
    }

    @Override
    public Observable<NotificationListWrapper> getNotificationList(final int page, final int pageSize) {
        return mNotificationApi.getNotifications(page, pageSize);
    }

    @Override
    public Observable<NotificationDetail> getNotificationDetail(String notificationId) {
        return mNotificationApi.getNotificationDetail(notificationId);
    }
}
