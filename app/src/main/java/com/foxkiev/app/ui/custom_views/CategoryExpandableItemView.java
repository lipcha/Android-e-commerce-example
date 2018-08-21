package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.foxkiev.app.model.models.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipcha on 27.02.18.
 */

public class CategoryExpandableItemView extends ExpandableView {

    private Category mCategory;
    private OnCategoryClickListener onCategoryClickListener;
    private Typeface typeface;

    public CategoryExpandableItemView(@NonNull Context context) {
        super(context);
    }

    public CategoryExpandableItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoryExpandableItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    void onOpenClick(View v) {
        if (mCategory == null)
            return;
        if (mCategory.hasChildrenCategory()){
            super.onOpenClick(v);
        }else {
            if (onCategoryClickListener != null)
                onCategoryClickListener.onCategoryClick(mCategory);
        }
    }

    public void setCategory(Category category){
        mCategory = category;
        renderCategory();
    }

    public void setOnCategoryClickListener(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
        updateChildViews();
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    private void renderCategory(){
        if (mCategory == null)
            return;
        setTitle(mCategory.getName());
        if (typeface != null)
            tvTitle.setTypeface(typeface);
        showArrow(mCategory.hasChildrenCategory());
        updateChildViews();
    }


    @Override
    public List<View> getChildViews() {
        final List<View> views = new ArrayList<>();
        if (mCategory != null){
            for (Category category : mCategory.getChildrenCategory()) {
                final CategoryExpandableItemView categoryExpandableItemView = new CategoryExpandableItemView(getContext());
                categoryExpandableItemView.setCategory(category);
                categoryExpandableItemView.setOnCategoryClickListener(onCategoryClickListener);
                views.add(categoryExpandableItemView);
            }
        }
        return views;
    }

    public interface OnCategoryClickListener{
        void onCategoryClick(Category category);
    }
}
