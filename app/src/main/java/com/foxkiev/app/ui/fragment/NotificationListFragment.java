package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseListFragment;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.base.list.ListConfiguration;
import com.foxkiev.app.model.models.Notification;
import com.foxkiev.app.presentation.presenter.NotificationListPresenter;
import com.foxkiev.app.presentation.view.NotificationListView;
import com.foxkiev.app.ui.adapters.notification.NotificationListAdapter;
import com.foxkiev.app.ui.adapters.notification.NotificationListConfiguration;

/**
 * Created by lipcha on 20.04.18.
 */

public class NotificationListFragment extends BaseListFragment<Notification, NotificationListAdapter> implements NotificationListView{

    @InjectPresenter
    NotificationListPresenter mNotificationsPresenter;

    public static NotificationListFragment newInstance(){
        return new NotificationListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_notification;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected String getTitle() {
        return getString(R.string.menu_item_notifications);
    }

    @Override
    protected BaseListPresenter<Notification, ?> getListPresenter() {
        return mNotificationsPresenter;
    }

    @Override
    protected NotificationListAdapter createListAdapter(DataProvider<Notification> dataProvider) {
        return new NotificationListAdapter(dataProvider);
    }

    @Override
    protected ListConfiguration createListConfiguration() {
        return new NotificationListConfiguration(getContext());
    }

    @Override
    public void onItemClick(Notification model, int position) {
        getRouter().addFragmentToBackStack(NotificationDetailFragment.newInstance(model.getId()));
    }
}
