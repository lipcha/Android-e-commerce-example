package com.foxkiev.app.ui.adapters.cart;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.model.models.CartItem;
import com.foxkiev.app.model.models.query.Query;
import com.foxkiev.app.ui.custom_views.PriceTextView;
import com.foxkiev.app.utils.ImageUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lipcha on 06.04.18.
 */

public class CartItemViewHolder extends BaseViewHolder<CartItem> {

    @BindView(R.id.tvProductName)
    TextView tvProductName;

    @BindView(R.id.tvProductPrice)
    PriceTextView tvProductPrice;

    @BindView(R.id.ivProductImage)
    ImageView ivProductImage;

    @BindView(R.id.tvProductCount)
    TextView tvProductCount;

    @BindView(R.id.pbUpdateItemCount)
    ProgressBar pbUpdateItemCount;

    @BindView(R.id.pbRemoveItem)
    ProgressBar pbRemoveItem;

    @BindView(R.id.btnRemoveCartItem)
    View btnRemoveCartItem;

    private int mPosition;

    public final CartItemViewCallback mCallback;

    public CartItemViewHolder(View itemView, CartItemViewCallback callback, MvpDelegate mvpDelegate) {
        super(itemView);
        mCallback = callback;
        tvProductPrice.init(mvpDelegate);
    }

    @Override
    protected void setupHolder(CartItem cartItem, int position, Query query) {
        mPosition = position;
        tvProductName.setText(cartItem.getName());
        tvProductPrice.setPrice(cartItem.getPrice() * cartItem.getQty());
        ImageUtils.loadProductImage(ivProductImage, cartItem.getImgUrl());
        tvProductCount.setText(String.valueOf(cartItem.getQty()));

        if (cartItem.isUpdateCartItemInProgress()){
            pbUpdateItemCount.setVisibility(View.VISIBLE);
            tvProductCount.setVisibility(View.INVISIBLE);
        }else {
            pbUpdateItemCount.setVisibility(View.INVISIBLE);
            tvProductCount.setVisibility(View.VISIBLE);
        }

        if (cartItem.isRemoveCArtItemInProgress()){
            btnRemoveCartItem.setVisibility(View.INVISIBLE);
            pbRemoveItem.setVisibility(View.VISIBLE);
        }else {
            btnRemoveCartItem.setVisibility(View.VISIBLE);
            pbRemoveItem.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick(R.id.btnRemoveCartItem)
    void onClickRemoveCartItem(){
        final CartItem cartItem = getData();
        if (mCallback != null && cartItem != null)
            mCallback.onClickRemoveCartItem(cartItem);
    }

    @OnClick(R.id.btnMinusProductCount)
    void onClickMinusCount(View v){
        final CartItem cartItem = getData();
        if (cartItem != null && !cartItem.isUpdateCartItemInProgress())
            mCallback.onClickMinusItemCount(cartItem);
    }

    @OnClick(R.id.btnAddProductCount)
    void onClickPlusCount(View v){
        final CartItem cartItem = getData();
        if (cartItem != null && !cartItem.isUpdateCartItemInProgress())
            mCallback.onClickPlusItemCount(cartItem);
    }

    @OnClick(R.id.tvProductCount)
    void onProductCountCkuck(View v){
        final CartItem cartItem = getData();
        if (cartItem != null && !cartItem.isUpdateCartItemInProgress())
            mCallback.onClickEditItemCount(cartItem);
    }
}
