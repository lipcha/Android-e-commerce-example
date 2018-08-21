package com.foxkiev.app.model.models.filter;

import java.util.List;

/**
 * Created by lipcha on 28.02.18.
 */

public class FilterGroup {

    private List<Filter> filters;

    public FilterGroup(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Filter> getFilters() {
        return filters;
    }
}
