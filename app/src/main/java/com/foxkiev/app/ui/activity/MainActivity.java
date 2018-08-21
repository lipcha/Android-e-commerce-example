package com.foxkiev.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.MainActivityRouter;
import com.foxkiev.app.base.Router;
import com.foxkiev.app.model.models.Currency;
import com.foxkiev.app.model.models.DefaultCurrency;
import com.foxkiev.app.model.models.Push;
import com.foxkiev.app.presentation.presenter.CartTotalPresenter;
import com.foxkiev.app.presentation.presenter.CurrencyPresenter;
import com.foxkiev.app.presentation.presenter.MainActivityPresenter;
import com.foxkiev.app.presentation.view.CartTotalView;
import com.foxkiev.app.presentation.view.CurrencyView;
import com.foxkiev.app.presentation.view.MainActivityView;
import com.foxkiev.app.ui.fragment.NotificationDetailFragment;
import com.foxkiev.app.utils.ViewUtils;
import com.ncapdevi.fragnav.FragNavController;

import butterknife.BindView;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by lipcha on 23.02.18.
 */

public class MainActivity extends BaseActivity implements MainActivityView, BottomNavigationView.OnNavigationItemSelectedListener, MainActivityRouter.UpdateBackButtonCallback, CartTotalView, CurrencyView{

    @InjectPresenter
    MainActivityPresenter mMainPresenter;

    @InjectPresenter(type = PresenterType.GLOBAL)
    CartTotalPresenter mCartCountPresenter;

    @InjectPresenter(type = PresenterType.GLOBAL)
    CurrencyPresenter mCurrencyPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.tlAuth)
    TabLayout tabLayout;

    private Router mRouter;

    private Badge mBadge;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerResId() {
        return R.id.flFragmentContainer;
    }

    @Override
    protected void init() {
        super.init();
        initToolbar();
        ViewUtils.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void afterViewCreated(@Nullable Bundle savedInstanceState) {
        mRouter = new MainActivityRouter(savedInstanceState, getSupportFragmentManager(), R.id.flFragmentContainer, this);
        handlePush(getIntent().getExtras());
    }

    @Override
    public Router getRouter() {
        return mRouter;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRouter != null && mRouter instanceof MainActivityRouter) {
            ((MainActivityRouter)mRouter).onSaveInstanceState(outState);
        }
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }
        setTitle(R.string.app_name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectTabItem(int position){
        switch (position){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                bottomNavigationView.setSelectedItemId(R.id.actionUserProfile);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionHome:
                setTab(FragNavController.TAB1);
                break;
            case R.id.actionSearch:
                setTab(FragNavController.TAB2);
                break;
            case R.id.actionNotifications:
                setTab(FragNavController.TAB3);
                break;
            case R.id.actionCart:
                setTab(FragNavController.TAB4);
                break;
            case R.id.actionUserProfile:
                setTab(FragNavController.TAB5);
                break;
        }
        return true;
    }

    public void onLogOut(){
        setTab(3);
        updateCartItemsCount();
        mCurrencyPresenter.updateCurrency();
    }

    public void setTab(int tabIndex){
        mMainPresenter.setTab(tabIndex);
    }

    @Override
    public void changeTab(int tab) {
        if (mRouter != null && mRouter instanceof MainActivityRouter){
            ((MainActivityRouter) mRouter).switchTab(tab);
        }
    }

    @Override
    public void onBackPressed() {
        if (!mRouter.popBackStack())
            super.onBackPressed();
    }

    public void updateCartItemsCount(){
        mCartCountPresenter.updateCartItemsCount();
    }

    @Override
    public void setCartItemsCount(int count) {
        if (mBadge == null){
            final ViewGroup bottomNavigationMenuView = (ViewGroup) bottomNavigationView.getChildAt(0);
            final View v = bottomNavigationMenuView.getChildAt(3); // number of menu from left
            mBadge = new QBadgeView(this)
                    .bindTarget(v)
                    .setGravityOffset(20, 0, true);
        }
        mBadge.setBadgeNumber(count);
        if (count == 0)
            mBadge.hide(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartItemsCount();
        mCurrencyPresenter.updateCurrency();
    }

    @Override
    public void onVisibleBack(boolean visible) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(visible);
        }
    }

    public TabLayout getTabLayout(){
        return tabLayout;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        final Bundle extras = intent.getExtras();
        handlePush(extras);
    }

    private void handlePush(final Bundle args){
        if (args == null || !args.containsKey(Constants.KEY_PUSH))
            return;
        final Push push = (Push) args.getSerializable(Constants.KEY_PUSH);
        if (push == null || TextUtils.isEmpty(push.getSourceType()) || TextUtils.isEmpty(push.getSourceId()))
            return;
        switch (push.getSourceType()){
            case "post":
                getRouter().addFragmentToBackStack(NotificationDetailFragment.newInstance(push.getSourceId()));
                break;
        }
    }

    @Override
    public void setCurrency(DefaultCurrency currency) {

    }
}
