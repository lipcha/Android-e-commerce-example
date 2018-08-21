package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;

import com.foxkiev.app.R;

/**
 * Created by lipcha on 22.02.18.
 */

public class EditTextWrapper extends TextInputLayout {

    public EditTextWrapper(Context context) {
        super(context);
    }

    public EditTextWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setError(@Nullable CharSequence error) {
        super.setError(error);
        if (error != null)
            startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
    }
}
