package com.foxkiev.app.ui.adapters.notification;

import android.view.View;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseAdapter;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.model.models.Notification;

/**
 * Created by lipcha on 20.04.18.
 */

public class NotificationListAdapter extends BaseAdapter {

    public NotificationListAdapter(DataProvider<Notification> dataProvider) {
        super(dataProvider);
    }

    @Override
    protected int getLayoutResId(int itemType) {
        return R.layout.item_notification;
    }

    @Override
    protected BaseViewHolder<Notification> getViewHolder(View itemView, int itemType) {
        return new NotificationListViewHolder(itemView);
    }
}
