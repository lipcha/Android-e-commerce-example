package com.foxkiev.app.model.api;

import com.foxkiev.app.model.models.NotificationDetail;
import com.foxkiev.app.model.models.NotificationListWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NotificationApi {

    @GET("blog/list")
    Observable<NotificationListWrapper> getNotifications(@Query("page") int page, @Query("size") int pagesize);

    @GET("blog/post/{postId}")
    Observable<NotificationDetail> getNotificationDetail(@Path("postId") String notificationId);
}
