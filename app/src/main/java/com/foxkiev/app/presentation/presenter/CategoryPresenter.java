package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.model.models.Category;
import com.foxkiev.app.model.repository.ShopRepository;
import com.foxkiev.app.presentation.view.CategoryView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by lipcha on 23.02.18.
 */

@InjectViewState
public class CategoryPresenter extends BaseListPresenter<Category, CategoryView> {

    @Inject
    ShopRepository mShopRepository;

    @Override
    public void refreshList() {
        getExecutor().execute(mShopRepository.getCategories(),
                this::onLoadCategorySuccess,
                this::onLoadListFailed
        );
    }

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        super.onAttachView();
    }

    private void onLoadCategorySuccess(final Category category) {
        onLoadListSuccess(category.getChildrenCategory().get(0).getChildrenCategory());
        getViewState().renderCategoryGroups(filterCategoryGroup(category.getChildrenCategory()));
    }

    private List<Category> filterCategoryGroup(List<Category> categories){
        final List<Category> categoriesGroup = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++){
            if (i != 0) {
                final Category category = categories.get(i);
                if (category.isActive() && category.isIncludeInMenu()){
                    categoriesGroup.add(category);
                }
            }
        }
        return categoriesGroup;
    }
}
