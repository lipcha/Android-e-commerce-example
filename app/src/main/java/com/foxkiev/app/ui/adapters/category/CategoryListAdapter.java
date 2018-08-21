package com.foxkiev.app.ui.adapters.category;

import android.view.View;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseAdapter;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.model.models.Category;

/**
 * Created by lipcha on 05.04.18.
 */

public class CategoryListAdapter extends BaseAdapter {

    public CategoryListAdapter(DataProvider<Category> dataProvider) {
        super(dataProvider);
    }

    @Override
    protected int getLayoutResId(int itemType) {
        return R.layout.item_category;
    }

    @Override
    protected BaseViewHolder<Category> getViewHolder(View itemView, int itemType) {
        return new CategoryListViewHolder(itemView);
    }
}
