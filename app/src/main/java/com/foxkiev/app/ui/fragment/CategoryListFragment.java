package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseListFragment;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.base.list.ListConfiguration;
import com.foxkiev.app.model.models.Category;
import com.foxkiev.app.presentation.presenter.CategoryPresenter;
import com.foxkiev.app.presentation.view.CategoryView;
import com.foxkiev.app.ui.adapters.category.CategoryGroupViewPgerAdapter;
import com.foxkiev.app.ui.adapters.category.CategoryListAdapter;
import com.foxkiev.app.ui.adapters.category.CategoryListConfiguration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lipcha on 05.04.18.
 */

public class CategoryListFragment extends BaseListFragment<Category, CategoryListAdapter> implements CategoryView {

    @BindView(R.id.categoryGroupContainer)
    View categoryGroupContainer;

    @BindView(R.id.vpCategoryGroup)
    ViewPager vpCategoryGroup;

    @InjectPresenter
    CategoryPresenter mCategoryPresenter;

    public static CategoryListFragment newInstance(){
        return new CategoryListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_category_list;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    protected String getTitle() {
        return getString(R.string.menu_item_home);
    }

    @Override
    protected BaseListPresenter<Category, ?> getListPresenter() {
        return mCategoryPresenter;
    }

    @Override
    protected CategoryListAdapter createListAdapter(DataProvider<Category> dataProvider) {
        return new CategoryListAdapter(dataProvider);
    }

    @Override
    protected ListConfiguration createListConfiguration() {
        return new CategoryListConfiguration(getContext());
    }

    @Override
    public void onItemClick(Category category, int position) {
        if (category.getProductCount() > 0)
            getRouter().addFragmentToBackStack(ProductListFragment.newInstance(category.getId(), category.getName()));
    }

    @Override
    public void renderCategoryGroups(List<Category> categories) {
        if (categories.isEmpty()){
            categoryGroupContainer.setVisibility(View.GONE);
            return;
        }
        categoryGroupContainer.setVisibility(View.VISIBLE);
        final FragmentPagerAdapter adapter = new CategoryGroupViewPgerAdapter(getChildFragmentManager(), categories);
        vpCategoryGroup.setAdapter(adapter);
    }
}
