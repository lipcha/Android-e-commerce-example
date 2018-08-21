package com.foxkiev.app.ui.adapters.product;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.model.models.ProductInfo;
import com.foxkiev.app.model.models.query.ProductQuery;
import com.foxkiev.app.model.models.query.Query;
import com.foxkiev.app.ui.custom_views.PriceTextView;
import com.foxkiev.app.utils.ImageUtils;
import com.foxkiev.app.utils.SearchHelper;

import butterknife.BindView;

/**
 * Created by lipcha on 28.02.18.
 */

public class ProductListViewHolder extends BaseViewHolder<ProductInfo> {

    @BindView(R.id.ivProductImage)
    ImageView ivProductImage;

    @BindView(R.id.tvProductName)
    TextView tvProductName;

    @BindView(R.id.tvProductPrice)
    PriceTextView tvProductPrice;

    @BindView(R.id.tvProductSpecialPrice)
    PriceTextView tvProductSpecialPrice;

    @BindView(R.id.messageNotAvailableProduct)
    View messageNotAvailableProduct;

    @BindView(R.id.messageProductNew)
    View messageProductNew;

    @BindView(R.id.tvProductSku)
    TextView tvProductSku;

    public ProductListViewHolder(View itemView, MvpDelegate mvpDelegate) {
        super(itemView);
        tvProductPrice.init(mvpDelegate);
        tvProductSpecialPrice.init(mvpDelegate);
    }

    @Override
    protected void setupHolder(ProductInfo productInfo, int position, Query query) {
        ImageUtils.loadProductImageWithBorder(ivProductImage, productInfo.getThumbnailUrl());
        if (query != null && query instanceof ProductQuery){
            tvProductName.setText(SearchHelper.highlight(((ProductQuery)query).getText(), productInfo.getName()));
        }
        else {
            tvProductName.setText(productInfo.getName());
        }
        messageProductNew.setVisibility(productInfo.isNew() ? View.VISIBLE : View.GONE);
        tvProductPrice.setPrice(productInfo.getPrice());
        if (productInfo.hasSpecialPrice()){
            tvProductPrice.setPaintFlags(tvProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvProductPrice.setTypeface(null, Typeface.NORMAL);
            tvProductSpecialPrice.setVisibility(View.VISIBLE);
            tvProductSpecialPrice.setPrice(productInfo.getSpecialPrice());
        }else {
            tvProductPrice.setPaintFlags(tvProductPrice.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            tvProductPrice.setTypeface(null, Typeface.BOLD);
            tvProductSpecialPrice.setVisibility(View.GONE);
        }

        messageNotAvailableProduct.setVisibility(productInfo.isInStock() ? View.GONE : View.VISIBLE);
        tvProductSku.setText(productInfo.getSku());
    }
}
