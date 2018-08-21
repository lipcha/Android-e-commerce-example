package com.foxkiev.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.foxkiev.app.ui.fragment.AuthenticationTabFragment;
import com.foxkiev.app.ui.fragment.CartFragment;
import com.foxkiev.app.ui.fragment.CategoryListFragment;
import com.foxkiev.app.ui.fragment.NotificationListFragment;
import com.foxkiev.app.ui.fragment.ProfileFragment;
import com.foxkiev.app.ui.fragment.SearchProductFragment;
import com.ncapdevi.fragnav.FragNavController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipcha on 06.04.18.
 */

public class MainActivityRouter implements Router {

    private FragNavController mFragNavController;

    private UpdateBackButtonCallback mCallback;

    public MainActivityRouter(@Nullable Bundle savedInstanceState, FragmentManager fragmentManager, int containerId, UpdateBackButtonCallback callback){
        mCallback = callback;
        final List<Fragment> fragments = new ArrayList<>(3);
        fragments.add(CategoryListFragment.newInstance());
        fragments.add(SearchProductFragment.newInstance());
        fragments.add(NotificationListFragment.newInstance());
        fragments.add(CartFragment.newInstance());
        fragments.add(AuthenticationTabFragment.newInstance());
        fragments.add(ProfileFragment.newInstance());

        mFragNavController = FragNavController
                .newBuilder(savedInstanceState, fragmentManager, containerId)
                .rootFragments(fragments)
                .build();
        updateBackButtonVisibleState();
    }

    public void onSaveInstanceState(Bundle outState){
        mFragNavController.onSaveInstanceState(outState);
    }

    @Override
    public void showRoot(){
        mFragNavController.clearStack();
    }

    public void switchTab(int index){
        mFragNavController.switchTab(index);
        updateBackButtonVisibleState();
    }

    @Override
    public void addFragmentToBackStack(BaseFragment fragment) {
        mFragNavController.pushFragment(fragment);
        updateBackButtonVisibleState();
    }

    @Override
    public boolean popBackStack() {
        if (mFragNavController.isRootFragment()){
            updateBackButtonVisibleState();
            return false;
        }
        else {
            mFragNavController.popFragment();
            updateBackButtonVisibleState();
            return true;
        }
    }

    private void updateBackButtonVisibleState(){
        if (mCallback == null)
            return;
        mCallback.onVisibleBack(!mFragNavController.isRootFragment());
    }

    public interface UpdateBackButtonCallback{
        void onVisibleBack(boolean visible);
    }
}
