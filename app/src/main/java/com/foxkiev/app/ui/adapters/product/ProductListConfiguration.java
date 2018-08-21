package com.foxkiev.app.ui.adapters.product;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.ListConfiguration;

/**
 * Created by lipcha on 26.04.18.
 */

public abstract class ProductListConfiguration implements ListConfiguration {

    @Override
    public int getEmptyStateIcon() {
        return R.drawable.ic_search_empty;
    }

    @Override
    public int getEmptyStateMessage() {
        return R.string.empty_state_search_products;
    }

    @Override
    public int getSearchEmptyStateIcon() {
        return R.drawable.ic_search_empty;
    }

    @Override
    public int getSearchEmptyStateMessage() {
        return R.string.empty_state_search_products;
    }
}
