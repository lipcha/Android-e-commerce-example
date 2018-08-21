package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.model.models.MyOrderItem;
import com.foxkiev.app.model.repository.OrdersRepository;
import com.foxkiev.app.presentation.view.MyOrdersView;

import javax.inject.Inject;

@InjectViewState
public class MyOrdersPresenter extends BaseListPresenter<MyOrderItem, MyOrdersView>{

    @Inject
    OrdersRepository mOrdersRepository;

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        super.onAttachView();
    }

    @Override
    public void refreshList() {
        getExecutor().execute(mOrdersRepository.getMyOrders(), ordersListWrapper -> onLoadListSuccess(ordersListWrapper.getItems()), this::onLoadListFailed);
    }
}
