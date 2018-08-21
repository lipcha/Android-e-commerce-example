package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxkiev.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lipcha on 02.03.18.
 */

public class ErrorView extends FrameLayout {

    @BindView(R.id.tvErrorMessage)
    TextView tvError;

    @BindView(R.id.tvOriginalError)
    TextView tvOriginalError;

    @BindView(R.id.ivErrorIcon)
    ImageView ivError;

    @DrawableRes
    private int mDrawableResId;

    @StringRes
    private int mMessageResId;

    private OnClickListener mOnReloadClickListener;

    public ErrorView(@NonNull Context context) {
        super(context);
    }

    public ErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ErrorView, 0, 0);
        mDrawableResId = a.getResourceId(R.styleable.ErrorView_icon, 0);
        mMessageResId = a.getResourceId(R.styleable.ErrorView_message, 0);

    }

    public ErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.view_error, this, true);
        ButterKnife.bind(this, this);
        tvError.setText(mMessageResId);
        if (mDrawableResId != 0)
            ivError.setImageResource(mDrawableResId);
//            ivError.setImageResource(ImageUtils.getVectorResourceDrawable(getContext(), mDrawableResId));
    }

    @OnClick(R.id.btnReload)
    void onReloadClick(View v){
        if (mOnReloadClickListener != null)
            mOnReloadClickListener.onClick(v);
    }

    public void show(Throwable throwable){
        setVisibility(VISIBLE);
        tvOriginalError.setText(throwable.getMessage());
    }

    public void setOnReloadClickListener(View.OnClickListener listener){
        mOnReloadClickListener = listener;
    }
}
