package com.foxkiev.app.ui.custom_views.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lipcha on 17.04.18.
 */

public class ConfirmDialog extends AppCompatDialogFragment {

    private static final String KEY_DIALOG_TITLE = "dialog_title";

    private static final String KEY_DIALOG_MESSAGE = "dialog_message";

    private static final String KEY_DIALOG_ICON = "dialog_icon";

    private static final String KEY_SHOW_CANCEL_BUTTON = "show_cancel";

    private static final String KEY_HEADER_BACKGROUND_COLOR = "header_background_color";

    private static final String KEY_TEXT_POSITIVE_BUTTON = "text_positive_button";

    private ConfirmCallback mConfirmCallback;

    private CancelCallback mCancelCallback;

    @BindView(R.id.tvDialogTitle)
    TextView tvDialogTitle;

    @BindView(R.id.tvDialogMessage)
    TextView tvDialogMessage;

    @BindView(R.id.ivConfirmDialogIcon)
    ImageView ivConfirmDialogIcon;

    @BindView(R.id.btnCancel)
    View btnCancel;

    @BindView(R.id.btnOk)
    Button btnOk;


    @BindView(R.id.dialogHeader)
    View dialogHeaderView;

    public static Builder newBuilder(final BaseActivity baseActivity){
        return new Builder(baseActivity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_Dialog_Alert);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.dialog_confirm, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle args = getArguments();
        if (args == null)
            return;

        final String title = args.getString(KEY_DIALOG_TITLE, "");
        final String message = args.getString(KEY_DIALOG_MESSAGE, "");
        final int iconResId = args.getInt(KEY_DIALOG_ICON, -1);
        final boolean showCancel = args.getBoolean(KEY_SHOW_CANCEL_BUTTON);
        final int headerBackgroundColor = args.getInt(KEY_HEADER_BACKGROUND_COLOR);
        final String textPositiveButton = args.getString(KEY_TEXT_POSITIVE_BUTTON);
        if (headerBackgroundColor != 0)
            dialogHeaderView.setBackgroundColor(headerBackgroundColor);

        btnCancel.setVisibility(showCancel ? View.VISIBLE : View.GONE);
        if (TextUtils.isEmpty(title)){
            tvDialogTitle.setVisibility(View.GONE);
        }else {
            tvDialogTitle.setText(title);
        }
        if (TextUtils.isEmpty(message)){
            tvDialogMessage.setVisibility(View.GONE);
        }else {
            tvDialogMessage.setText(message);
        }
        if (!TextUtils.isEmpty(textPositiveButton))
            btnOk.setText(textPositiveButton);

        if (iconResId == -1 || iconResId == 0)
            ivConfirmDialogIcon.setVisibility(View.GONE);
        else if (getContext() != null){
            ivConfirmDialogIcon.setVisibility(View.VISIBLE);
            Glide.with(getContext())
                    .load(iconResId)
                    .into(ivConfirmDialogIcon);
        }
    }

    @OnClick(R.id.btnCancel)
    void onCancelClick(View v){
        if (mCancelCallback != null)
            mCancelCallback.onCancel();
        dismiss();
    }

    @OnClick(R.id.btnOk)
    void onClickOk(View v){
        if (mConfirmCallback != null)
            mConfirmCallback.onConfirm();
        dismiss();
    }

    public void setConfirmCallback(final ConfirmCallback confirmCallback){
        mConfirmCallback = confirmCallback;
    }

    public void setCancelCallback(final CancelCallback cancelCallback){
        mCancelCallback = cancelCallback;
    }

    public interface ConfirmCallback{
        void onConfirm();
    }

    public interface CancelCallback{
        void onCancel();
    }

    public static class Builder{

        private BaseActivity mBaseActivity;

        private String mMessage;
        private String mTitle;
        private String mTextPositiveButton;
        private int mIconResId;
        private int headerBackgroundColor;
        private boolean visibleCancelButton = true;
        private ConfirmCallback mConfirmCallback;
        private CancelCallback mCancelCallback;

        public Builder(BaseActivity baseActivity) {
            this.mBaseActivity = baseActivity;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setMessage(@StringRes int messageResId) {
            this.mMessage = mBaseActivity.getString(messageResId);
            return this;
        }

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setTitle(@StringRes int titleResId) {
            this.mTitle = mBaseActivity.getString(titleResId);
            return this;
        }

        public Builder setIcon(int iconResId) {
            this.mIconResId = iconResId;
            return this;
        }

        public Builder setHeaderBackgroundColor(int headerBackgroundColor) {
            this.headerBackgroundColor = headerBackgroundColor;
            return this;
        }

        public Builder setVisibleCancelButton(boolean visibleCancel){
            this.visibleCancelButton = visibleCancel;
            return this;
        }

        public Builder setConfirmCallback(ConfirmCallback confirmCallback) {
            this.mConfirmCallback = confirmCallback;
            return this;
        }

        public Builder setCancelCallback(CancelCallback cancelCallback) {
            this.mCancelCallback = cancelCallback;
            return this;
        }

        public Builder setTextPositiveButton(String text){
            this.mTextPositiveButton = text;
            return this;
        }

        public Builder setTextPositiveButton(@StringRes int textResId){
            this.mTextPositiveButton = mBaseActivity.getString(textResId);
            return this;
        }

        public void show(){
            final ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setConfirmCallback(mConfirmCallback);
            confirmDialog.setCancelCallback(mCancelCallback);
            final Bundle args = new Bundle();
            if (mTitle != null)
                args.putString(KEY_DIALOG_TITLE, mTitle);
            if (mMessage != null)
                args.putString(KEY_DIALOG_MESSAGE, mMessage);
            if (mTextPositiveButton != null)
                args.putString(KEY_TEXT_POSITIVE_BUTTON, mTextPositiveButton);
            args.putInt(KEY_HEADER_BACKGROUND_COLOR, headerBackgroundColor);
            args.putBoolean(KEY_SHOW_CANCEL_BUTTON, visibleCancelButton);
            args.putInt(KEY_DIALOG_ICON, mIconResId);
            confirmDialog.setArguments(args);
            confirmDialog.show(mBaseActivity.getSupportFragmentManager(), ConfirmDialog.class.getName());
        }
    }
}
