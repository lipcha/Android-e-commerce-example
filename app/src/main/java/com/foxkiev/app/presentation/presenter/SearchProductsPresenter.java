package com.foxkiev.app.presentation.presenter;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;

import java.util.Collections;

@InjectViewState
public class SearchProductsPresenter extends ProductListPresenter{

    private String mSearchQuery;

    public SearchProductsPresenter() {
        super(null);
    }

    public void setSearchQuery(String searchQuery){
        mSearchQuery = searchQuery;
        refreshList();
    }

    @Override
    public void refreshList() {
        if (TextUtils.isEmpty(mSearchQuery)){
            onLoadListSuccess(Collections.emptyList());
            return;
        }
        getExecutor().execute(mShopRepository.getProductsByQuery(mSearchQuery),
                this::onLoadListSuccess,
                this::onLoadListFailed
        );
    }
}
