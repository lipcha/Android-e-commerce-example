package com.foxkiev.app.ui.adapters.my_orders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.model.models.Address;
import com.foxkiev.app.model.models.MyOrderItem;
import com.foxkiev.app.model.models.query.Query;
import com.foxkiev.app.ui.custom_views.PriceTextView;
import com.foxkiev.app.utils.TimeUtils;

import butterknife.BindView;

public class MyOrderViewHolder extends BaseViewHolder<MyOrderItem>{

    @BindView(R.id.tvOrderId)
    TextView tvOrderId;

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.tvOrderTotal)
    PriceTextView tvOrderTotal;

    @BindView(R.id.tvCreatedAt)
    TextView tvCreatedAt;

    public MyOrderViewHolder(View itemView, MvpDelegate mvpDelegate) {
        super(itemView);
        tvOrderTotal.init(mvpDelegate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setupHolder(MyOrderItem myOrderItem, int position, Query<MyOrderItem> query) {
        tvOrderId.setText("№ " + myOrderItem.getId());
        final Address address = myOrderItem.getBillingAddress();
        if (address != null)
            tvUserName.setText(address.getFirstName() + " " + address.getLastName());
        tvOrderTotal.setPrice(myOrderItem.getBaseGrandTotal());
        tvCreatedAt.setText(TimeUtils.getFormattedDate(myOrderItem.getCreatedAt()));
    }
}
