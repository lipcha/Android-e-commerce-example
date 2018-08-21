package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foxkiev.app.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lipcha on 05.03.18.
 */

public class ExpandableView extends FrameLayout{

    @BindView(R.id.tvExpandedTitle)
    TextView tvTitle;

    @BindView(R.id.llViewContainer)
    LinearLayout llViewContainer;

    @BindView(R.id.ivArrow)
    ImageView ivArrow;

    public ExpandableView(@NonNull Context context) {
        super(context);
        init();
    }

    public ExpandableView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExpandableView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_expandable, this, true);
        ButterKnife.bind(this, this);
        updateChildViews();
    }

    public void showArrow(boolean show){
        ivArrow.setVisibility(show ? VISIBLE : GONE);
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public List<View> getChildViews(){
        return Collections.EMPTY_LIST;
    }

    @OnClick(R.id.btnOpen)
    void onOpenClick(View v){
        TransitionManager.beginDelayedTransition(llViewContainer);

        if (llViewContainer.getVisibility() == VISIBLE){
            llViewContainer.setVisibility(GONE);

            ivArrow.animate().rotation(0f).start();
        }else {
            llViewContainer.setVisibility(VISIBLE);
            ivArrow.animate().rotation(90f).start();
        }
    }

    public void updateChildViews(){
        llViewContainer.removeAllViews();
        final List<View> views = getChildViews();
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (View view : views){
            llViewContainer.addView(view, layoutParams);
        }
    }
}
