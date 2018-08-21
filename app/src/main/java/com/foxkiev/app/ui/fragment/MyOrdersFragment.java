package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.base.list.BaseListFragment;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.base.list.ListConfiguration;
import com.foxkiev.app.model.models.MyOrderItem;
import com.foxkiev.app.presentation.presenter.MyOrdersPresenter;
import com.foxkiev.app.presentation.view.MyOrdersView;
import com.foxkiev.app.ui.adapters.my_orders.MyOrdersListAdapter;
import com.foxkiev.app.ui.adapters.my_orders.MyOrdersListConfiguration;

public class MyOrdersFragment extends BaseListFragment<MyOrderItem, MyOrdersListAdapter> implements MyOrdersView{

    @InjectPresenter
    MyOrdersPresenter mMyOrdersPresenter;

    public static BaseFragment newInstance(){
        return new MyOrdersFragment();
    }

    @Override
    protected BaseListPresenter<MyOrderItem, ?> getListPresenter() {
        return mMyOrdersPresenter;
    }

    @Override
    protected MyOrdersListAdapter createListAdapter(DataProvider<MyOrderItem> dataProvider) {
        return new MyOrdersListAdapter(dataProvider, getMvpDelegate());
    }

    @Override
    protected ListConfiguration createListConfiguration() {
        return new MyOrdersListConfiguration(getContext());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my_orders;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_my_orders);
    }
}
