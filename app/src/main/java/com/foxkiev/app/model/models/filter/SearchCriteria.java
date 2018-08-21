package com.foxkiev.app.model.models.filter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lipcha on 28.02.18.
 */

public class SearchCriteria {

    @SerializedName("filter_groups")
    private FilterGroup filterGroup;

    public SearchCriteria(FilterGroup filterGroup) {
        this.filterGroup = filterGroup;
    }

    public FilterGroup getFilterGroup() {
        return filterGroup;
    }
}
