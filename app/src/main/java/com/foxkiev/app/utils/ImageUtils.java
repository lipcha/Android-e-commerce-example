package com.foxkiev.app.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.foxkiev.app.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by lipcha on 28.02.18.
 */

public class ImageUtils {

    public static void loadProductImageWithBorder(final ImageView imageView, final String imageUrl){
        final MultiTransformation<Bitmap> multiTransformation = new MultiTransformation<>(new CenterCrop(), new RoundedCornersTransformation(16, 0));
        final Context context = imageView.getContext();
        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(multiTransformation)
                        .error(ContextCompat.getDrawable(context, R.drawable.ic_no_photo)))
                .into(imageView);
    }

    public static void loadProductImage(final ImageView imageView, final String imageUrl){
        final Context context = imageView.getContext();
        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.centerCropTransform()
                        .error(ContextCompat.getDrawable(context, R.drawable.ic_no_photo)))
                .into(imageView);
    }

    public static void loadProductImage(final Context context, final SubsamplingScaleImageView imageView, final String imageUrl){

        final SimpleTarget<Drawable> target = new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                imageView.setImage(ImageSource.bitmap(drawableToBitmap(resource)));
            }
        };

        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(target);
    }

    public static void loadCategoryImage(final ImageView imageView, final String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);
    }

    public static void loadNotificationImage(final Context context, final ImageView imageView, final String imageUrl) {
        final MultiTransformation<Bitmap> multiTransformation = new MultiTransformation<>(new CenterCrop(), new RoundedCornersTransformation(16, 0));
        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions
                        .bitmapTransform(multiTransformation)
                        .placeholder(getVectorResourceDrawable(context, R.drawable.ic_notification_placeholder))
                        .error(getVectorResourceDrawable(context, R.drawable.ic_notification_placeholder))
                )
                .into(imageView);
    }

    public static void loadNotificationImageHeader(final Context context, final ImageView imageView, final String imageUrl) {
        final MultiTransformation<Bitmap> multiTransformation = new MultiTransformation<>(new CenterCrop());
        Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions
                        .bitmapTransform(multiTransformation)
                        .placeholder(getVectorResourceDrawable(context, R.drawable.ic_notification_placeholder))
                        .error(getVectorResourceDrawable(context, R.drawable.ic_notification_placeholder))
                )
                .into(imageView);
    }

    public static Drawable getVectorResourceDrawable(final Context context, final int resId){
        return VectorDrawableCompat.create(context.getResources(), resId, null);
    }

    public static Bitmap getBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }
}
