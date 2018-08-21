package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.ui.activity.MainActivity;
import com.foxkiev.app.ui.adapters.pager_adapters.AuthenticationTabPagerAdapter;

import butterknife.BindView;

/**
 * Created by lipcha on 03.05.18.
 */

public class AuthenticationTabFragment extends BaseFragment{

    public static BaseFragment newInstance(){
        return new AuthenticationTabFragment();
    }

    @BindView(R.id.vpAuth)
    ViewPager viewPager;

    private AuthenticationTabPagerAdapter mTabPagerAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_authentication_tab;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mTabPagerAdapter = new AuthenticationTabPagerAdapter(getChildFragmentManager(), getContext());
        viewPager.setAdapter(mTabPagerAdapter);
        ((MainActivity)getBaseActivity()).getTabLayout().setupWithViewPager(viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getBaseActivity()).getTabLayout().setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getBaseActivity()).getTabLayout().setVisibility(View.GONE);
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_my_account);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.account_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                getRouter().addFragmentToBackStack(SettingsFragment.newInstance());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
