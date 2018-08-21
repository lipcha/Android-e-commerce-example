package com.foxkiev.app.ui.custom_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foxkiev.app.R;
import com.foxkiev.app.model.models.CartSegment;
import com.foxkiev.app.model.models.CartTotal;
import com.foxkiev.app.utils.ViewUtils;

import java.util.List;

/**
 * Created by lipcha on 01.05.18.
 */

public class CartTotalView extends LinearLayout {

    public CartTotalView(@NonNull Context context) {
        super(context);
    }

    public CartTotalView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CartTotalView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CartTotalView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @SuppressLint("SetTextI18n")
    public void renderCartTotal(final CartTotal cartTotal){
        setOrientation(VERTICAL);
        removeAllViews();
        final List<CartSegment> cartSegments = cartTotal.getTotalSegments();

        if (cartSegments == null || cartSegments.isEmpty()){
            return;
        }
        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        for (CartSegment segment : cartSegments){
            addCartSegmentView(cartTotal, segment, layoutInflater);
        }
    }

    @SuppressLint("SetTextI18n")
    private void addCartSegmentView(CartTotal cartTotal, CartSegment segment, LayoutInflater layoutInflater){
        if (TextUtils.isEmpty(segment.getValue()) || segment.getValue().equals("0"))
            return;

        final View item = layoutInflater.inflate(R.layout.item_cart_total, this, false);
        final TextView tvTitle = item.findViewById(R.id.tvCartTotalTitle);
        final TextView tvValue = item.findViewById(R.id.tvCartTotalValue);
        if (segment.getCode().equals("discount")) {
            tvTitle.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
            tvValue.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
        }

        if (segment.getArea() != null && segment.getArea().equals("footer")) {
            addDivider();
            tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
            tvValue.setTypeface(tvValue.getTypeface(), Typeface.BOLD);
        }
        tvTitle.setText(segment.getTitle() + ":" );
        tvValue.setText(segment.getValue() + " " + cartTotal.getQuoteCurrencyCode());
        addView(item);

    }

    private void addDivider(){
        final View divider = new View(getContext());
        final LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.divider_height));
        divider.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.divider_color));
        final int padding = ViewUtils.convertDpToPixels(4, getContext());
        layoutParams.setMargins(0, padding, 0, padding);
        addView(divider, layoutParams);
    }
}
