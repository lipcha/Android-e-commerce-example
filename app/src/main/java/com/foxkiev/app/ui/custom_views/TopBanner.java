package com.foxkiev.app.ui.custom_views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foxkiev.app.R;
import com.foxkiev.app.utils.ImageUtils;

/**
 * Created by lipcha on 25.04.18.
 */

public class TopBanner {

    private static final int LayoutParamFlags =

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?

            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED :

            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

    private String mMessage;
    private int mIconResId;
    private int mIndicatorColor;
    private Context mContext;

    public static TopBanner newInstance(Context context, String message){
        return new TopBanner(context, message, 0, 0);
    }

    public TopBanner(@NonNull Context context, String message, int iconResId, int indicatorColor) {
        mContext = context;
        mMessage = message;
        mIconResId = iconResId;
        mIndicatorColor = indicatorColor;
    }

    public void show(){
        @SuppressLint("InflateParams")
        final View rootView = LayoutInflater.from(mContext).inflate(R.layout.view_top_banner, null);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PRIORITY_PHONE,
                LayoutParamFlags,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null)
            return;
        try {
            wm.addView(rootView, params);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(mContext, mMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        showBannerWithAnimation(rootView);

        final Handler handler = new Handler();
        handler.postDelayed(() -> hideBannerWithAnimation(rootView, wm), 2000);

        final TextView tvMessage = rootView.findViewById(R.id.tvBannerMessage);
        final ImageView ivIcon = rootView.findViewById(R.id.ivBannerIcon);
        final View indicator = rootView.findViewById(R.id.vBannerColorIndicator);

        tvMessage.setText(mMessage);
        if (mIconResId != 0)
            ivIcon.setImageDrawable(ImageUtils.getVectorResourceDrawable(mContext, mIconResId));
        if (mIndicatorColor != 0)
            indicator.setBackgroundColor(ContextCompat.getColor(mContext, mIndicatorColor));
    }

    private void showBannerWithAnimation(final View rootView){
        final ViewTreeObserver textViewTreeObserver = rootView.getViewTreeObserver();
        textViewTreeObserver.addOnGlobalLayoutListener(() -> {
            rootView.setY((-1) * rootView.getHeight());
            rootView.animate()
                    .translationY(0)
                    .setDuration(600)
                    .setInterpolator(new BounceInterpolator())
                    .start();
        });
    }

    private void hideBannerWithAnimation(final View rootView, final WindowManager wm){
        rootView.animate()
                .translationY((-1) * rootView.getHeight())
                .setDuration(600)
                .setInterpolator(new LinearInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rootView.setVisibility(View.GONE);
                        try {
                            wm.removeView(rootView);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                })
                .start();

    }
}
