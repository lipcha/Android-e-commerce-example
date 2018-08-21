package com.foxkiev.app.ui.fragment;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.base.list.BaseAdapter;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.model.models.IdModel;
import com.foxkiev.app.model.models.Product;
import com.foxkiev.app.model.models.ProductInfo;
import com.foxkiev.app.presentation.presenter.CartPresenter;
import com.foxkiev.app.presentation.presenter.ProductDetailPresenter;
import com.foxkiev.app.presentation.view.CartView;
import com.foxkiev.app.presentation.view.ProductDetailView;
import com.foxkiev.app.ui.activity.MainActivity;
import com.foxkiev.app.ui.adapters.product.ProductListAdapter;
import com.foxkiev.app.ui.custom_views.AddToCartButton;
import com.foxkiev.app.ui.custom_views.ErrorView;
import com.foxkiev.app.ui.custom_views.ExpandableView;
import com.foxkiev.app.ui.custom_views.PriceTextView;
import com.foxkiev.app.ui.custom_views.ProductImageViewPager;
import com.foxkiev.app.utils.ErrorHandler;
import com.foxkiev.app.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lipcha on 28.02.18.
 */

public class ProductDetailFragment extends BaseFragment implements ProductDetailView, CartView {

    private static final String PRODUCT_SKU_KEY = "product_sku_key";

    @BindView(R.id.productImageViewPager)
    ProductImageViewPager productImageViewPager;

    @BindView(R.id.tvProductName)
    TextView tvProductName;

    @BindView(R.id.tvProductSku)
    TextView tvProductSku;

    @BindView(R.id.tvProductDescription)
    TextView tvProductDescription;

    @BindView(R.id.tvProductPrice)
    PriceTextView tvProductPrice;

    @BindView(R.id.tvProductSpecialPrice)
    PriceTextView tvProductSpecialPrice;

    @BindView(R.id.llProductDetailContainer)
    View llProductDetailContainer;

    @BindView(R.id.progressBar)
    View progressBar;

    @BindView(R.id.errorView)
    ErrorView errorView;

    @BindView(R.id.btnAddToCart)
    AddToCartButton btnAddToCart;

    @BindView(R.id.llProductInformationContainer)
    ViewGroup llProductInformationContainer;

    @BindView(R.id.llRelatedProductsContainer)
    View llRelatedProductsContainer;

    @BindView(R.id.relatedProductRecyclerView)
    RecyclerView relatedProductRecyclerView;

    @InjectPresenter
    ProductDetailPresenter mProductDetailPresenter;

    @InjectPresenter
    CartPresenter mCartPresenter;

    public static BaseFragment newInstance(final String productSku){
        final BaseFragment fragment = new ProductDetailFragment();
        final Bundle args = new Bundle();
        args.putString(PRODUCT_SKU_KEY, productSku);
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    ProductDetailPresenter provideProductDetailPresenter(){
        return new ProductDetailPresenter(getArguments().getString(PRODUCT_SKU_KEY));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_product_detail;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        tvProductPrice.init(getMvpDelegate());
        tvProductSpecialPrice.init(getMvpDelegate());
        errorView.setOnReloadClickListener(v -> mProductDetailPresenter.refreshProduct());
        btnAddToCart.setAddToCartClickListener((product, productCount) -> mCartPresenter.addProductToCart(product, productCount));
    }

    @Override
    protected String getTitle() {
        return "Product detail";
    }

    @Override
    public void beforeLoadProduct() {
        llProductDetailContainer.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadProductError(Throwable throwable) {
        progressBar.setVisibility(View.GONE);
        errorView.show(throwable);
    }

    @Override
    public void renderProduct(Product product) {
        llProductDetailContainer.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        btnAddToCart.configureWithProduct(product);
        productImageViewPager.setupWithProduct(product);
        tvProductName.setText(product.getName());
        tvProductSku.setText(product.getSku());
        tvProductDescription.setText(Html.fromHtml(product.getCustomAttribute("description")));
        tvProductPrice.setPrice(product.getPrice());
        if (product.hasSpecialPrice()){
            tvProductPrice.setPaintFlags(tvProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvProductPrice.setTypeface(null, Typeface.NORMAL);
            tvProductSpecialPrice.setVisibility(View.VISIBLE);
            tvProductSpecialPrice.setPrice(product.getSpecialPrice());
        }else {
            tvProductPrice.setPaintFlags(tvProductPrice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            tvProductPrice.setTypeface(null, Typeface.BOLD);
            tvProductSpecialPrice.setVisibility(View.GONE);
        }
//        renderCustomAttributes(product);
    }

    @Override
    public void renderRelatedProducts(List<ProductInfo> relatedProducts) {
        if (relatedProducts == null || relatedProducts.isEmpty() || getActivity() == null)
            return;
        llRelatedProductsContainer.setVisibility(View.VISIBLE);
        final int windowWidth = ViewUtils.getWindowWidth(getActivity());
        final ProductListAdapter productListAdapter = new ProductListAdapter(new DataProvider<>(relatedProducts), getMvpDelegate()){
            @Override
            protected BaseViewHolder<ProductInfo> getViewHolder(View itemView, int itemType) {
                itemView.getLayoutParams().width = (int) (windowWidth / 2.4);
                return super.getViewHolder(itemView, itemType);
            }
        };
        productListAdapter.setListMode(Constants.MODE_GRID);
        productListAdapter.setOnClickListener((BaseAdapter.OnItemClickListener<ProductInfo>) (productInfo, position) ->
                getRouter().addFragmentToBackStack(ProductDetailFragment.newInstance(productInfo.getSku()))
        );
        relatedProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        relatedProductRecyclerView.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
    }

    private void renderCustomAttributes(final Product product){
        llProductInformationContainer.addView(new ExpandableView(getContext()){
            @Override
            public List<View> getChildViews() {
                final List<View> views = new ArrayList<>();
                final TextView tvDescription = new TextView(getContext());
                tvDescription.setText(Html.fromHtml(product.getCustomAttribute("description")));
                views.add(tvDescription);
                return views;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.product_detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:

                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAddToCartInProgress(String productSku) {
        btnAddToCart.showAddToCartProgress(productSku);
    }

    @Override
    public void onAddToCartSuccess() {
        btnAddToCart.hideAddToCatProgress();
        final BaseActivity activity = getBaseActivity();
        if (activity instanceof MainActivity)
            ((MainActivity)activity).updateCartItemsCount();
    }

    @Override
    public void onAddToCartError(String productSky, Throwable throwable) {
        btnAddToCart.hideAddToCatProgress();
        ErrorHandler.onError(getContext(), throwable);
    }
}
