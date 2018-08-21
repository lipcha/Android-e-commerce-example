package com.foxkiev.app.ui.adapters.notification;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.model.models.NotificationDetail;
import com.foxkiev.app.model.models.query.Query;
import com.foxkiev.app.utils.ImageUtils;

import butterknife.BindView;

public class NotificationHeaderViewHolder extends BaseViewHolder<NotificationDetail>{

    @BindView(R.id.tvNotificationTitle)
    TextView tvNotificationTitle;

    @BindView(R.id.tvNotificationDescription)
    TextView tvNotificationDescription;

    @BindView(R.id.tvNotificationContent)
    TextView tvNotificationContent;

    @BindView(R.id.ivNotificationImage)
    ImageView ivNotificationImage;

    public NotificationHeaderViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void setupHolder(NotificationDetail notificationDetail, int position, Query<NotificationDetail> query) {
        tvNotificationTitle.setText(notificationDetail.getTitle());
        tvNotificationDescription.setText(notificationDetail.getShortDescription());

        if (TextUtils.isEmpty(notificationDetail.getContent())){
            tvNotificationContent.setVisibility(View.GONE);
        }else {
            tvNotificationContent.setText(Html.fromHtml(notificationDetail.getContent()));
        }

        if (TextUtils.isEmpty(notificationDetail.getImageUrl())){
            ivNotificationImage.setVisibility(View.GONE);
        }else {
            ImageUtils.loadNotificationImageHeader(getContext(), ivNotificationImage, notificationDetail.getImageUrl());
        }
    }
}
