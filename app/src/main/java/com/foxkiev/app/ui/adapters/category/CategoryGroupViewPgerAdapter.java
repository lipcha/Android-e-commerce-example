package com.foxkiev.app.ui.adapters.category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.foxkiev.app.model.models.Category;
import com.foxkiev.app.ui.fragment.CategoryGroupFragment;

import java.util.List;

public class CategoryGroupViewPgerAdapter extends FragmentPagerAdapter {

    private final List<Category> mCategories;

    public CategoryGroupViewPgerAdapter(FragmentManager fm, List<Category> categories) {
        super(fm);
        mCategories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryGroupFragment.newInstance(mCategories.get(position));
    }

    @Override
    public int getCount() {
        return mCategories.size();
    }

    @Override
    public float getPageWidth(int position) {
        return(0.33f);
    }
}
