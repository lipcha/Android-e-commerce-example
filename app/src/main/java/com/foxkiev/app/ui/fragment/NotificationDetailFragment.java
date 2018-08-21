package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.base.list.BaseListFragment;
import com.foxkiev.app.base.list.BaseListPresenter;
import com.foxkiev.app.base.list.DataProvider;
import com.foxkiev.app.base.list.ListConfiguration;
import com.foxkiev.app.model.models.NotificationDetail;
import com.foxkiev.app.model.models.ProductInfo;
import com.foxkiev.app.presentation.presenter.ListModePresenter;
import com.foxkiev.app.presentation.presenter.NotificationDetailPresenter;
import com.foxkiev.app.presentation.view.ListModeView;
import com.foxkiev.app.presentation.view.NotificationDetailView;
import com.foxkiev.app.ui.adapters.notification.NotificationDetailAdapter;
import com.foxkiev.app.ui.adapters.product.ProductListConfiguration;

public class NotificationDetailFragment extends BaseListFragment<ProductInfo, NotificationDetailAdapter> implements NotificationDetailView, ListModeView {

    private static final String KEY_NOTIFICATION_ID = "notification_id";

    @InjectPresenter
    NotificationDetailPresenter mNotificationDetailPresenter;

    @InjectPresenter
    ListModePresenter mListModePresenter;

    private MenuItem modeMenuItem;

    @ProvidePresenter
    NotificationDetailPresenter provideNotificationDetailPresenter(){
        return new NotificationDetailPresenter(getArguments().getString(KEY_NOTIFICATION_ID));
    }

    public static BaseFragment newInstance(final String notificationId){
        final BaseFragment fragment = new NotificationDetailFragment();
        final Bundle args = new Bundle();
        args.putString(KEY_NOTIFICATION_ID, notificationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BaseListPresenter<ProductInfo, ?> getListPresenter() {
        return mNotificationDetailPresenter;
    }

    @Override
    protected NotificationDetailAdapter createListAdapter(DataProvider<ProductInfo> dataProvider) {
        return new NotificationDetailAdapter(dataProvider, getMvpDelegate());
    }

    @Override
    protected ListConfiguration createListConfiguration() {
        return new ProductListConfiguration() {
            @Override
            public RecyclerView.LayoutManager getLayoutManager() {
                final RecyclerView.LayoutManager layoutManager =  mListModePresenter.getMode() == Constants.MODE_LIST ? new LinearLayoutManager(getContext()) : new GridLayoutManager(getContext(), 2);

                if (layoutManager instanceof GridLayoutManager){
                    ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            switch(position){
                                case 0:
                                    return 2;
                                default:
                                    return 1;
                            }
                        }
                    });
                }
                return layoutManager;
            }
        };
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_notification_detail;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getAdapter().setListMode(mListModePresenter.getMode());
        getRecyclerView().setItemAnimator(new DefaultItemAnimator());
        setHasOptionsMenu(true);
    }

    @Override
    protected String getTitle() {
        return "Notification detail";
    }

    @Override
    public void onItemClick(ProductInfo productWrapper, int position) {
        getRouter().addFragmentToBackStack(ProductDetailFragment.newInstance(productWrapper.getSku()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.notification_detail_menu, menu);
        modeMenuItem = menu.findItem(R.id.btnListMode);
        updateModeMenuItem(mListModePresenter.getMode());
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnListMode:
                mListModePresenter.onClickChangeListMode(((LinearLayoutManager)getRecyclerView().getLayoutManager()).findFirstCompletelyVisibleItemPosition());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateModeMenuItem(int mode){
        if (modeMenuItem == null)
            return;
        switch (mode){
            case Constants.MODE_LIST:
                modeMenuItem.setTitle(R.string.menu_item_grid);
                modeMenuItem.setIcon(R.drawable.ic_grid);
                break;
            case Constants.MODE_GRID:
                modeMenuItem.setTitle(R.string.menu_item_list);
                modeMenuItem.setIcon(R.drawable.ic_list);
                break;
        }
    }

    @Override
    public void changeListMode(int mode, int position) {
        updateModeMenuItem(mode);
        getRecyclerView().setLayoutManager(getListConfiguration().getLayoutManager());
        getAdapter().setListMode(mode);
        getRecyclerView().setAdapter(getAdapter());
        notifyDataSetChange();
        getRecyclerView().scrollToPosition(position);
    }

    @Override
    public void renderNotificationHeader(NotificationDetail notificationDetail) {
        getAdapter().setNotificationDetail(notificationDetail);
    }

    @Override
    protected void updateEmptyState() {

    }
}
