package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.model.models.MediaGallery;
import com.foxkiev.app.model.models.Product;
import com.foxkiev.app.ui.activity.MediaViewActivity;
import com.foxkiev.app.utils.ImageUtils;
import com.foxkiev.app.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by lipcha on 02.03.18.
 */

public class ProductImageViewPager extends FrameLayout {

    @BindView(R.id.imageViewPager)
    ViewPager imageViewPager;

    @BindView(R.id.circlePagerIndicator)
    CircleIndicator circleIndicator;

    public ProductImageViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public ProductImageViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProductImageViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.view_product_image_pager, this, true);
        ButterKnife.bind(this, this);
    }

    public void setupWithProduct(final Product product){
        final List<MediaGallery> mediaGalleries = product.getMediaGalleries();
        imageViewPager.setAdapter(new ProductImagePagerAdapter(getContext(), mediaGalleries));
        if (mediaGalleries.size() > 1)
            circleIndicator.setViewPager(imageViewPager);
    }

    private static class ProductImagePagerAdapter extends PagerAdapter{

        private List<MediaGallery> mediaGalleries;
        private Context mContext;

        public ProductImagePagerAdapter(Context context, List<MediaGallery> mediaGalleries) {
            this.mediaGalleries = mediaGalleries;
            mContext = context;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            if (mediaGalleries.isEmpty()){
                final ImageView imageView = new ImageView(mContext);
                container.addView(imageView, 0);
                imageView.setImageResource(R.drawable.ic_no_photo);
                return imageView;
            }
            final ImageView imageView = new ImageView(mContext);
            RxUtils.setupClick(imageView, v -> MediaViewActivity.startActivity(mContext, new ArrayList<>(mediaGalleries), position));
            container.addView(imageView, 0);
            ImageUtils.loadProductImage(imageView, Constants.API.BASE_MEDIA_URL + mediaGalleries.get(position).getFile());
            return imageView;
        }

        @Override
        public int getCount() {
            if (mediaGalleries.isEmpty())
                return 1;
            return mediaGalleries.size();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
