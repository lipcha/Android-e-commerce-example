package com.foxkiev.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;
import com.foxkiev.app.base.Router;
import com.foxkiev.app.model.models.MediaGallery;
import com.foxkiev.app.ui.fragment.ImageViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class MediaViewActivity extends BaseActivity{

    private static final String KEY_MEDIA_GALLERY_LIST = "media_gallery_list";
    private static final String KEY_CURRENT_POSITION = "current_position";

    public static void startActivity(final Context context, final ArrayList<MediaGallery> mediaGalleries, int position){
        final Intent intent = new Intent(context, MediaViewActivity.class);
        intent.putParcelableArrayListExtra(KEY_MEDIA_GALLERY_LIST, mediaGalleries);
        intent.putExtra(KEY_CURRENT_POSITION, position);
        context.startActivity(intent);
    }

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.circlePagerIndicator)
    CircleIndicator circlePagerIndicator;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.appBarLayout)
    View appBarLayout;

    private boolean isUp;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_media_view;
    }

    @Override
    protected int getFragmentContainerResId() {
        return 0;
    }

    @Override
    protected void afterViewCreated(@Nullable Bundle savedInstanceState) {
        initToolbar();
        final List<MediaGallery> mediaGalleries = getIntent().getParcelableArrayListExtra(KEY_MEDIA_GALLERY_LIST);
        final int position = getIntent().getIntExtra(KEY_CURRENT_POSITION, 0);
        final MediaPagerAdapter pagerAdapter = new MediaPagerAdapter(getSupportFragmentManager(), mediaGalleries);
        viewPager.setAdapter(pagerAdapter);
        if (mediaGalleries.size() > 1)
            circlePagerIndicator.setViewPager(viewPager);
        if (position != 0)
            viewPager.setCurrentItem(position);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(R.string.app_name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Router getRouter() {
        return null;
    }

    public void toggleToolbarVisible() {
        if (isUp) {
            slideDown(appBarLayout);
        } else {
            slideUp(appBarLayout);
        }
        isUp = !isUp;

    }

    public void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        final TranslateAnimation animate = new TranslateAnimation(
                0,
                0,
                0,
                -view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public void slideDown(View view){
        final TranslateAnimation animate = new TranslateAnimation(
                0,
                0,
                -view.getHeight(),
                0); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }


    private static class MediaPagerAdapter extends FragmentPagerAdapter{

        private final List<MediaGallery> mMediaGalleries;

        public MediaPagerAdapter(FragmentManager fm, final List<MediaGallery> mediaGalleries) {
            super(fm);
            this.mMediaGalleries = mediaGalleries;
        }

        @Override
        public Fragment getItem(int position) {
            return ImageViewFragment.newInstance(mMediaGalleries.get(position));
        }

        @Override
        public int getCount() {
            return mMediaGalleries.size();
        }
    }
}
