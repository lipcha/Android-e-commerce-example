package com.foxkiev.app.ui.adapters.notification;

import android.support.annotation.NonNull;
import android.view.View;

import com.arellomobile.mvp.MvpDelegate;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseAdapter;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.model.models.IdModel;
import com.foxkiev.app.model.models.NotificationDetail;
import com.foxkiev.app.model.models.ProductInfo;
import com.foxkiev.app.ui.adapters.product.ProductListViewHolder;

public class NotificationDetailAdapter extends BaseAdapter {

    private static final int ITEM_TYPE_NOTIFICATION_HEADER = 89;

    private NotificationDetail mNotificationDetail;

    private int currentItemType;

    private final MvpDelegate mMvpDelegate;

    public NotificationDetailAdapter(DataProvider<ProductInfo> dataProvider, MvpDelegate mvpDelegate) {
        super(dataProvider);
        mMvpDelegate = mvpDelegate;
    }

    public void setNotificationDetail(NotificationDetail notificationDetail) {
        this.mNotificationDetail = notificationDetail;
        notifyItemInserted(0);
    }

    public void setListMode(int listMode) {
        this.currentItemType = listMode;
    }

    @Override
    protected int getLayoutResId(int itemType) {
        switch (itemType){
            case Constants.MODE_LIST:
                return  R.layout.item_product_list;
            case Constants.MODE_GRID:
                return R.layout.item_product_grid;
            case ITEM_TYPE_NOTIFICATION_HEADER:
                return R.layout.item_notification_header;
            default:
                return  R.layout.item_product_list;
        }
    }

    @Override
    protected BaseViewHolder<? extends IdModel> getViewHolder(View itemView, int itemType) {
        if (itemType == ITEM_TYPE_NOTIFICATION_HEADER)
            return new NotificationHeaderViewHolder(itemView);
        return new ProductListViewHolder(itemView, mMvpDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (position == 0 && mNotificationDetail != null)
            holder.init(mNotificationDetail, 0, null);
        else
            super.onBindViewHolder(holder, mNotificationDetail != null ? position - 1 : position);
    }

    @Override
    public int getItemCount() {
        return  (mNotificationDetail == null) ? super.getItemCount() : super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 && mNotificationDetail!= null ? ITEM_TYPE_NOTIFICATION_HEADER : currentItemType;
    }

}
