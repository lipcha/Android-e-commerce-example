package com.foxkiev.app.model.repository;

import com.foxkiev.app.model.models.NotificationDetail;
import com.foxkiev.app.model.models.NotificationListWrapper;

import io.reactivex.Observable;

public interface NotificationRepository {

    Observable<NotificationListWrapper> getNotificationList(final int page, final int pageSize);

    Observable<NotificationDetail> getNotificationDetail(final String notificationId);
}
