package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseListFragment;
import com.foxkiev.app.presentation.presenter.ProductListPresenter;
import com.foxkiev.app.presentation.presenter.SearchProductsPresenter;
import com.foxkiev.app.presentation.view.ProductListView;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchProductFragment extends ProductListFragment implements ProductListView {

    @BindView(R.id.etSearchQuery)
    EditText etSearchQuery;

    public static BaseListFragment newInstance(){
        return new SearchProductFragment();
    }

    @Override
    ProductListPresenter provideProductPresenter(){
        return new SearchProductsPresenter();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search_product;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.afterViewCreated(view, savedInstanceState);
    }

    @Override
    protected String getTitle() {
        return "Search";
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.actionSearch).setVisible(false);
    }

    @OnClick(R.id.btnSearch)
    void onClickSearch(View v){
        if (mProductListPresenter instanceof SearchProductsPresenter){
            ((SearchProductsPresenter)mProductListPresenter).setSearchQuery(etSearchQuery.getText().toString());
        }
    }
}
