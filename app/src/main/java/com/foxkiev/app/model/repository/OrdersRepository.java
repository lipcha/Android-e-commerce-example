package com.foxkiev.app.model.repository;

import com.foxkiev.app.model.models.OrdersListWrapper;

import io.reactivex.Observable;


public interface OrdersRepository {

    Observable<OrdersListWrapper> getMyOrders();
}
