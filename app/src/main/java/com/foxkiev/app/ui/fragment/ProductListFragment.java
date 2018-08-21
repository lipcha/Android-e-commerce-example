package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.base.list.BaseListFragment;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.base.list.ListConfiguration;
import com.foxkiev.app.model.models.FilterModel;
import com.foxkiev.app.model.models.ProductInfo;
import com.foxkiev.app.model.models.query.ProductQuery;
import com.foxkiev.app.presentation.presenter.ListModePresenter;
import com.foxkiev.app.presentation.presenter.ProductListPresenter;
import com.foxkiev.app.presentation.view.ListModeView;
import com.foxkiev.app.presentation.view.ProductListView;
import com.foxkiev.app.ui.adapters.product.ProductListAdapter;
import com.foxkiev.app.ui.adapters.product.ProductListConfiguration;
import com.foxkiev.app.ui.custom_views.FilterView;

import butterknife.BindView;

/**
 * Created by lipcha on 28.02.18.
 */

public class ProductListFragment extends BaseListFragment<ProductInfo, ProductListAdapter> implements ProductListView, ListModeView, FilterView.OnFilterChangeListener {

    private static final String CATEGORY_ID_KEY = "category_id_key";
    private static final String CATEGORY_NAME_KEY = "category_name_key";

    @InjectPresenter
    ProductListPresenter mProductListPresenter;

    @InjectPresenter
    ListModePresenter mListModePresenter;

    @BindView(R.id.filterDrawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.filterView)
    FilterView filterView;

    private MenuItem modeMenuItem;

    public static BaseFragment newInstance(final String categoryId, final String categoryName){
        final BaseFragment fragment = new ProductListFragment();
        final Bundle args = new Bundle();
        args.putString(CATEGORY_ID_KEY, categoryId);
        args.putString(CATEGORY_NAME_KEY, categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    ProductListPresenter provideProductPresenter(){
        return new ProductListPresenter(getArguments().getString(CATEGORY_ID_KEY));
    }

    @Override
    protected BaseListPresenter<ProductInfo, ?> getListPresenter() {
        return mProductListPresenter;
    }

    @Override
    protected ProductListAdapter createListAdapter(DataProvider<ProductInfo> dataProvider) {
        return new ProductListAdapter(dataProvider, getMvpDelegate());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_product_list;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getAdapter().setListMode(mListModePresenter.getMode());
        getRecyclerView().setItemAnimator(new DefaultItemAnimator());
        setHasOptionsMenu(true);
        filterView.setOnFilterChangeListener(this);
    }

    @Override
    protected String getTitle() {
        return getArguments().getString(CATEGORY_NAME_KEY);
    }

    @Override
    public void onItemClick(ProductInfo productInfo, int position) {
        getRouter().addFragmentToBackStack(ProductDetailFragment.newInstance(productInfo.getSku()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.product_list_menu, menu);
        modeMenuItem = menu.findItem(R.id.btnListMode);
        updateModeMenuItem(mListModePresenter.getMode());
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnListMode:
                mListModePresenter.onClickChangeListMode(((LinearLayoutManager)getRecyclerView().getLayoutManager()).findFirstCompletelyVisibleItemPosition());
                return true;
            case R.id.btnProductFilter:
                toggleFilterDrawer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleFilterDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.END))
            drawerLayout.closeDrawer(Gravity.END);
        else drawerLayout.openDrawer(GravityCompat.END);
    }

    @Override
    public void changeListMode(int mode, int position) {
        updateModeMenuItem(mode);
        getRecyclerView().setLayoutManager(getListConfiguration().getLayoutManager());
        getAdapter().setListMode(mode);
        getRecyclerView().setAdapter(getAdapter());
        notifyDataSetChange();
        getRecyclerView().scrollToPosition(position);
    }

    private void updateModeMenuItem(int mode){
        if (modeMenuItem == null)
            return;
        switch (mode){
            case Constants.MODE_LIST:
                modeMenuItem.setTitle(R.string.menu_item_grid);
                modeMenuItem.setIcon(R.drawable.ic_grid);
                break;
            case Constants.MODE_GRID:
                modeMenuItem.setTitle(R.string.menu_item_list);
                modeMenuItem.setIcon(R.drawable.ic_list);
                break;
        }
    }

    @Override
    protected void onSearchTextChange(String text) {
        getListPresenter().setQuery(new ProductQuery(text));
    }

    @Override
    protected ListConfiguration createListConfiguration() {
        return new ProductListConfiguration() {
            @Override
            public RecyclerView.LayoutManager getLayoutManager() {
                return mListModePresenter.getMode() == Constants.MODE_LIST ? new LinearLayoutManager(getContext()) : new GridLayoutManager(getContext(), 2);
            }
        };
    }

    @Override
    public void onFilterChange(FilterModel filterModel) {
        mProductListPresenter.setFilter(filterModel);
    }

    @Override
    public void renderFilter(FilterModel filterModel) {
        filterView.renderFilter(filterModel);
    }
}
