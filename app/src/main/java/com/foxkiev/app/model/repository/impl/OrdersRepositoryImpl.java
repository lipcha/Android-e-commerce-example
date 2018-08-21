package com.foxkiev.app.model.repository.impl;

import com.foxkiev.app.model.api.OrdersApi;
import com.foxkiev.app.model.models.OrdersListWrapper;
import com.foxkiev.app.model.repository.OrdersRepository;
import com.foxkiev.app.model.storage.PreferenceStorage;
import com.foxkiev.app.utils.Optional;

import io.reactivex.Observable;

public class OrdersRepositoryImpl implements OrdersRepository {

    private final OrdersApi mOrdersApi;
    private final PreferenceStorage mPreferenceStorage;

    public OrdersRepositoryImpl(OrdersApi ordersApi, PreferenceStorage preferenceStorage) {
        this.mOrdersApi = ordersApi;
        this.mPreferenceStorage = preferenceStorage;
    }

    @Override
    public Observable<OrdersListWrapper> getMyOrders() {
        return mPreferenceStorage.getCustomer()
                .map(Optional::get)
                .flatMap(customer -> mOrdersApi.getMyOrders(100, 0, "customer_id", String.valueOf(customer.getId())));
    }
}
