package com.foxkiev.app.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.Category;

import butterknife.BindView;

public class CategoryGroupFragment extends BaseFragment{

    public static final String KEY_CATEGORY = "category";

    @BindView(R.id.tvCategoryGroupName)
    TextView tvCategoryGroupName;

    @BindView(R.id.ivCategoryGroupImage)
    ImageView ivCategoryGroupImage;

    @BindView(R.id.btnCategoryGroup)
    View btnCategoryGroup;

    public static BaseFragment newInstance(Category category){
        final BaseFragment fragment = new CategoryGroupFragment();
        final Bundle args = new Bundle();
        args.putSerializable(KEY_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_category_group;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Bundle args = getArguments();
        if (args == null)
            return;
        final Category category = (Category) args.getSerializable(KEY_CATEGORY);
        if (category == null)
            return;

        final String categoryName = category.getName();

        tvCategoryGroupName.setText(categoryName.substring(0,1).toUpperCase() + categoryName.substring(1).toLowerCase());
        switch (category.getName()){
            case "Акция":
                ivCategoryGroupImage.setImageResource(R.drawable.ic_discount);
                break;
            case "Новинки":
                ivCategoryGroupImage.setImageResource(R.drawable.ic_new);
                break;
            case "РАСПРОДАЖА":
                ivCategoryGroupImage.setImageResource(R.drawable.ic_sale);
                break;
        }

        setupClick(btnCategoryGroup, v -> {
            if (category.getProductCount() > 0)
                getRouter().addFragmentToBackStack(ProductListFragment.newInstance(category.getId(), category.getName()));
        });
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
