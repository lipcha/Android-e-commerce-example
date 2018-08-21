package com.foxkiev.app.ui.adapters.notification;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.model.models.Notification;
import com.foxkiev.app.model.models.query.Query;
import com.foxkiev.app.utils.ImageUtils;

import butterknife.BindView;

/**
 * Created by lipcha on 20.04.18.
 */

public class NotificationListViewHolder extends BaseViewHolder<Notification> {

    @BindView(R.id.tvNotificationTitle)
    TextView tvTitle;

    @BindView(R.id.tvNotificationDescription)
    TextView tvDescription;

    @BindView(R.id.ivNotificationImage)
    ImageView ivNotificationImage;

    public NotificationListViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void setupHolder(Notification notification, int position, Query query) {
        tvTitle.setText(notification.getTitle());
        tvDescription.setText(notification.getShortDescription());
        ImageUtils.loadNotificationImage(getContext(), ivNotificationImage, notification.getImageUrl());
    }
}
