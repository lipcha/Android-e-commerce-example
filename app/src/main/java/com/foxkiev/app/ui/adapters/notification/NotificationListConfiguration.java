package com.foxkiev.app.ui.adapters.notification;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.ListConfiguration;

/**
 * Created by lipcha on 26.04.18.
 */

public class NotificationListConfiguration implements ListConfiguration {

    private final Context mContext;

    public NotificationListConfiguration(Context context) {
        this.mContext = context;
    }

    @Override
    public int getEmptyStateIcon() {
        return R.drawable.ic_no_notifications;
    }

    @Override
    public int getEmptyStateMessage() {
        return R.string.empty_state_notifications;
    }

    @Override
    public int getSearchEmptyStateIcon() {
        return 0;
    }

    @Override
    public int getSearchEmptyStateMessage() {
        return 0;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }
}
