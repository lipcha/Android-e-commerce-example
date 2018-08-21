package com.foxkiev.app.ui.adapters.category;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.list.BaseViewHolder;
import com.foxkiev.app.model.models.Category;
import com.foxkiev.app.model.models.query.Query;
import com.foxkiev.app.utils.ImageUtils;

import butterknife.BindView;

/**
 * Created by lipcha on 05.04.18.
 */

public class CategoryListViewHolder extends BaseViewHolder<Category> {

    @BindView(R.id.ivCategoryImage)
    ImageView ivCategoryImage;

    @BindView(R.id.tvCategoryName)
    TextView tvCategoryName;

    public CategoryListViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void setupHolder(Category category, int position, Query query) {
        tvCategoryName.setText(category.getName());
        ImageUtils.loadProductImage(ivCategoryImage, category.getImageUrl());
    }
}
