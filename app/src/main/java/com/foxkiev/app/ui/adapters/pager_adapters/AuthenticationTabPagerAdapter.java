package com.foxkiev.app.ui.adapters.pager_adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.foxkiev.app.R;
import com.foxkiev.app.ui.fragment.SignInFragment;
import com.foxkiev.app.ui.fragment.SignUpFragment;

/**
 * Created by lipcha on 03.05.18.
 */

public class AuthenticationTabPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public AuthenticationTabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return SignInFragment.newInstance(false);
            case 1:
                return SignUpFragment.newInstance();
        }
        return SignInFragment.newInstance(false);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.title_sign_in);
            case 1:
                return mContext.getString(R.string.title_create_account);

        }
        return super.getPageTitle(position);
    }
}
