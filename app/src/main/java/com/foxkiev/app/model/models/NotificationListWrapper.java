package com.foxkiev.app.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationListWrapper {

    @SerializedName("items")
    private List<Notification> notifications;

    @SerializedName("has_next_page")
    private boolean hasNextPage;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }
}
