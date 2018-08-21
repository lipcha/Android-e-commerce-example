package com.foxkiev.app.model.models;

import com.foxkiev.app.model.models.filter.SearchCriteria;

import java.util.List;

public class OrdersListWrapper {

    private List<MyOrderItem> items;

    private SearchCriteria searchCriteria;

    private int totalCount;

    public List<MyOrderItem> getItems() {
        return items;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
