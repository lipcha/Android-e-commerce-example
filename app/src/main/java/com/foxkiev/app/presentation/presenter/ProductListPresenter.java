package com.foxkiev.app.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.foxkiev.app.App;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.model.models.FilterModel;
import com.foxkiev.app.model.models.ProductInfo;
import com.foxkiev.app.model.repository.ShopRepository;
import com.foxkiev.app.presentation.view.ProductListView;
import com.foxkiev.app.utils.product_comparators.ProductNameComparator;
import com.foxkiev.app.utils.product_comparators.ProductPriceComparator;
import com.foxkiev.app.utils.product_comparators.ProductSkuComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by lipcha on 28.02.18.
 */

@InjectViewState
public class ProductListPresenter extends BaseListPresenter<ProductInfo, ProductListView>{

    @Inject
    ShopRepository mShopRepository;

    private final String mCategoryId;

    private FilterModel mFilter;

    public ProductListPresenter(String categoryId) {
        this.mCategoryId = categoryId;
    }

    @Override
    protected void onAttachView() {
        App.getRepositoryComponent().inject(this);
        setFilter(new FilterModel());
        super.onAttachView();
    }

    @Override
    public void refreshList() {
        getExecutor().execute(mShopRepository.getCategoryProducts(mCategoryId),
                this::onLoadListSuccess,
                this::onLoadListFailed
        );
    }

    @Override
    protected void onLoadListSuccess(List<ProductInfo> data) {
        super.onLoadListSuccess(data);
        getViewState().renderFilter(mFilter);
    }

    public void setFilter(FilterModel filter){
        mFilter = filter;
        Comparator<ProductInfo> productInfoComparator = null;
        switch (filter.getSortBy()){
            case FilterModel.SORT_BY_POSITION:

                break;
            case FilterModel.SORT_BY_PRODUCT_NAME:
                productInfoComparator = new ProductNameComparator();
                break;
            case FilterModel.SORT_BY_SKU:
                productInfoComparator = new ProductSkuComparator();
                break;
            case FilterModel.SORT_BY_PRICE:
                productInfoComparator = new ProductPriceComparator();
                break;
            case FilterModel.SORT_BY_SIZE:

                break;
            case FilterModel.SORT_BY_CUSTOM_STOCK_STATUS:

                break;
        }
        if (productInfoComparator == null)
            return;
        Collections.sort(getDataProvider().getData(), filter.isSortReverse() ? productInfoComparator : Collections.reverseOrder(productInfoComparator));
        getViewState().notifyDataSetChange();
    }

}
