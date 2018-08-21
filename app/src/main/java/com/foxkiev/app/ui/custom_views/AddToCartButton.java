package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.model.models.Product;
import com.foxkiev.app.model.models.StockItem;
import com.foxkiev.app.utils.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lipcha on 02.03.18.
 */

public class AddToCartButton extends FrameLayout {

    @BindView(R.id.etProductCount)
    EditText etProductCount;

    @BindView(R.id.pbAddToCart)
    View pbAddToCart;

    @BindView(R.id.buttonAddToCart)
    Button btnAddToCart;

    @BindViews({
            R.id.etProductCount,
            R.id.buttonAddToCart,
            R.id.btnMinusProductCount,
            R.id.btnAddProductCount,
            R.id.llButtonWrapper}
            )
    List<View> views;

    private Product mProduct;

    private AddToCartClickListener addToCartClickListener;

    public AddToCartButton(@NonNull Context context) {
        super(context);
    }

    public AddToCartButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AddToCartButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AddToCartButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.view_add_to_cart, this, true);
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.btnMinusProductCount)
    void onClickMinusProductCount(View v){
        final int count = getCount();
        if (count > 1)
            etProductCount.setText(String.valueOf(count - 1));
    }

    @OnClick(R.id.btnAddProductCount)
    void onClickAddProductCount(View v){
        final int count = getCount();
        if (count < Constants.MAX_CART_PRODUCT)
            etProductCount.setText(String.valueOf(count + 1));
    }

    @OnClick(R.id.buttonAddToCart)
    void onclickAddToCart(View v){
        final int count = getCount();
        if (count < 1 || count > Constants.MAX_CART_PRODUCT){
            etProductCount.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
            return;
        }
        if (addToCartClickListener != null && mProduct != null)
            addToCartClickListener.onAddToCart(mProduct, count);
    }

    public void showAddToCartProgress(final String productSky){
        if (TextUtils.isEmpty(productSky) || mProduct == null || !mProduct.getSku().equals(productSky))
            return;
        btnAddToCart.setEnabled(false);
        btnAddToCart.setVisibility(INVISIBLE);
        pbAddToCart.setVisibility(VISIBLE);
    }

    public void hideAddToCatProgress(){
        btnAddToCart.setEnabled(true);
        btnAddToCart.setVisibility(VISIBLE);
        pbAddToCart.setVisibility(GONE);
    }

    public void setAddToCartClickListener(AddToCartClickListener addToCartClickListener) {
        this.addToCartClickListener = addToCartClickListener;
    }

    private int getCount(){
        final String countString = etProductCount.getText().toString();
        if (TextUtils.isEmpty(countString))
            return 0;
        return Integer.parseInt(etProductCount.getText().toString());
    }

    public void configureWithProduct(Product product){
        mProduct = product;

        final StockItem stockItem = mProduct.getStockItem();
        if (stockItem != null){
            if (stockItem.isInStock()){
                ButterKnife.apply(views, ViewUtils.ENABLE);
                btnAddToCart.setText(R.string.btn_name_add_to_cart);
            }else {
                ButterKnife.apply(views, ViewUtils.DISABLE);
                btnAddToCart.setText(R.string.btn_name_out_of_stock);
            }
        }
    }

    public interface AddToCartClickListener{
        void onAddToCart(Product product, int productCount);
    }
}
