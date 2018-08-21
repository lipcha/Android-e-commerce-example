package com.foxkiev.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;


import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseFragment;
import com.foxkiev.app.model.models.MediaGallery;
import com.foxkiev.app.ui.activity.MediaViewActivity;
import com.foxkiev.app.utils.ImageUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ImageViewFragment extends BaseFragment {

    private static final String KEY_MEDIA_GALLERY = "media_gallery";

    @BindView(R.id.scaleImageView)
    SubsamplingScaleImageView subsamplingScaleImageView;

    public static BaseFragment newInstance(final MediaGallery mediaGallery){
        final BaseFragment fragment = new ImageViewFragment();
        final Bundle args = new Bundle();
        args.putParcelable(KEY_MEDIA_GALLERY, mediaGallery);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_image_view;
    }

    @Override
    protected void afterViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Bundle args = getArguments();
        if (args == null)
            return;
        final MediaGallery mediaGallery = args.getParcelable(KEY_MEDIA_GALLERY);
        if (mediaGallery == null)
            return;

        ImageUtils.loadProductImage(getContext(), subsamplingScaleImageView, Constants.API.BASE_MEDIA_URL + mediaGallery.getFile());
    }

    @OnClick(R.id.scaleImageView)
    void onClick(View v){
        if (getActivity() instanceof MediaViewActivity){
            ((MediaViewActivity)getActivity()).toggleToolbarVisible();
        }
    }

    @Override
    protected String getTitle() {
        return null;
    }
}
