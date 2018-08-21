package com.foxkiev.app.ui.adapters.product;

import android.view.View;

import com.arellomobile.mvp.MvpDelegate;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseAdapter;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.model.models.ProductInfo;

/**
 * Created by lipcha on 28.02.18.
 */

public class ProductListAdapter extends BaseAdapter {

    private int currentItemType;

    private final MvpDelegate mMvpDelegate;

    public ProductListAdapter(DataProvider<ProductInfo> dataProvider, MvpDelegate mvpDelegate) {
        super(dataProvider);
        mMvpDelegate = mvpDelegate;
    }

    public void setListMode(int listMode) {
        this.currentItemType = listMode;
    }

    @Override
    protected int getLayoutResId(int itemType) {
        switch (itemType){
            case Constants.MODE_LIST:
                return  R.layout.item_product_list;
            case Constants.MODE_GRID:
                return R.layout.item_product_grid;
                default:
                    return  R.layout.item_product_list;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return currentItemType;
    }

    @Override
    protected BaseViewHolder<ProductInfo> getViewHolder(View itemView, int itemType) {
        return new ProductListViewHolder(itemView, mMvpDelegate);
    }
}
