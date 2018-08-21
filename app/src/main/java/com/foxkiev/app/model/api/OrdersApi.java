package com.foxkiev.app.model.api;

import com.foxkiev.app.Constants;
import com.foxkiev.app.model.models.OrderInformation;
import com.foxkiev.app.model.models.OrdersListWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrdersApi {

    @GET(Constants.API.REQUESTS.GET_MY_ORDERS)
    Observable<OrdersListWrapper> getMyOrders(
            @Query("searchCriteria[pageSize]") int pageSize,
            @Query("searchCriteria[currentPage]") int currentPage,
            @Query("searchCriteria[filter_groups][0][filters][0][field]") String fieldName,
            @Query("searchCriteria[filter_groups][0][filters][0][value]") String fieldValue
    );


    @GET("orders/{orderId}")
    Observable<OrderInformation> getOrderInformation(@Path("orderId") String orderId);
}
