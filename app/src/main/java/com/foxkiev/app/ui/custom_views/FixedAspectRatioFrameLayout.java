package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.foxkiev.app.R;

/**
 * Created by lipcha on 01.03.18.
 */

public class FixedAspectRatioFrameLayout  extends FrameLayout
{
    private int mAspectRatioWidth;
    private int mAspectRatioHeight;

    public FixedAspectRatioFrameLayout(Context context)
    {
        super(context);
    }

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        Init(context, attrs);
    }

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        Init(context, attrs);
    }

    private void Init(Context context, AttributeSet attrs)
    {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FixedAspectRatioFrameLayout);

        mAspectRatioWidth = a.getInt(R.styleable.FixedAspectRatioFrameLayout_aspectRatioWidth, 3);
        mAspectRatioHeight = a.getInt(R.styleable.FixedAspectRatioFrameLayout_aspectRatioHeight, 2);

        a.recycle();
    }
    // **overrides**

    @Override protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int receivedWidth = MeasureSpec.getSize(widthMeasureSpec);
        int receivedHeight = MeasureSpec.getSize(heightMeasureSpec);

        float aspectWidth = mAspectRatioWidth;
        float aspectHeight = mAspectRatioHeight;

        float aspectRatio = aspectWidth / aspectHeight;

        int measuredWidth;
        int measuredHeight;
        boolean widthDynamic;
        if (heightMode == MeasureSpec.EXACTLY) {
            if (widthMode == MeasureSpec.EXACTLY) {
                widthDynamic = receivedWidth == 0;
            } else {
                widthDynamic = true;
            }
        } else if (widthMode == MeasureSpec.EXACTLY) {
            widthDynamic = false;
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        if (widthDynamic) {
            // Width is dynamic.
            int w = (int) (receivedHeight * aspectRatio);
            measuredWidth = MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY);
            measuredHeight = heightMeasureSpec;
        } else {
            // Height is dynamic.
            measuredWidth = widthMeasureSpec;
            int h = (int) (receivedWidth / aspectRatio);
            measuredHeight = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
        }
        super.onMeasure(measuredWidth, measuredHeight);
    }
}
