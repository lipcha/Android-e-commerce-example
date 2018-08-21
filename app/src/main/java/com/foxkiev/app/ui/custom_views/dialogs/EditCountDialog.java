package com.foxkiev.app.ui.custom_views.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.foxkiev.app.Constants;
import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lipcha on 24.04.18.
 */

public class EditCountDialog extends AppCompatDialogFragment {

    private static final String KEY_DIALOG_MIN_VALUE = "dialog_min_value";

    private static final String KEY_DIALOG_MAX_VALUE = "dialog_max_value";

    private static final String KEY_DIALOG_INIT_VALUE = "dialog_init_value";

    private EditCountDialog.CountCallback mCountCallback;

    private EditCountDialog.CancelCallback mCancelCallback;

    @BindView(R.id.etCount)
    EditText etCount;

    @BindView(R.id.etCountWrapper)
    TextInputLayout etCountWrapper;

    private int mMinValue = 1;
    private int mMaxValue = Constants.MAX_CART_PRODUCT;
    private int mInitValue;

    public static void show(final BaseActivity activity, final int initValue, final int minValue, final int maxValue, final EditCountDialog.CountCallback callback){
        show(activity, initValue, minValue, maxValue, callback, null);
    }


    public static void show(final BaseActivity activity, final int initValue, final int minValue, final int maxValue, final EditCountDialog.CountCallback callback, final EditCountDialog.CancelCallback cancelCallback) {
        final EditCountDialog dialog = new EditCountDialog();
        dialog.setCountCallback(callback);
        dialog.setCancelCallback(cancelCallback);
        final Bundle args = new Bundle();
        args.putInt(KEY_DIALOG_INIT_VALUE, initValue);
        args.putInt(KEY_DIALOG_MIN_VALUE, minValue);
        args.putInt(KEY_DIALOG_MAX_VALUE, maxValue);
        dialog.setArguments(args);
        dialog.show(activity.getSupportFragmentManager(), ConfirmDialog.class.getName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_Dialog_Alert);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.dialog_edit_count, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle args = getArguments();
        if (args == null)
            return;
        mInitValue = args.getInt(KEY_DIALOG_INIT_VALUE);
        mMinValue = args.getInt(KEY_DIALOG_MIN_VALUE, 1);
        mMaxValue = args.getInt(KEY_DIALOG_MAX_VALUE, Constants.MAX_CART_PRODUCT);

        etCount.setText(String.valueOf(mInitValue));
    }

    @OnClick(R.id.btnCancel)
    void onCancelClick(View v){
        if (mCancelCallback != null)
            mCancelCallback.onCancel();
        dismiss();
    }

    @OnClick(R.id.btnOk)
    void onClickOk(View v){
        final String value = etCount.getText().toString();
        if (TextUtils.isEmpty(value))
            return;

        final int intValue = Integer.parseInt(value);
        if (intValue < mMinValue || intValue > mMaxValue)
            return;
        if (intValue == mInitValue){
            dismiss();
            return;
        }

        if (mCountCallback != null)
            mCountCallback.onNewCount(intValue);
        dismiss();
    }

    public void setCountCallback(final EditCountDialog.CountCallback callback){
        mCountCallback = callback;
    }

    public void setCancelCallback(final EditCountDialog.CancelCallback cancelCallback){
        mCancelCallback = cancelCallback;
    }

    public interface CountCallback{
        void onNewCount(int count);
    }

    public interface CancelCallback{
        void onCancel();
    }
}