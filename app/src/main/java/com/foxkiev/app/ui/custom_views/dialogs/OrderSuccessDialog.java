package com.foxkiev.app.ui.custom_views.dialogs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foxkiev.app.R;
import com.foxkiev.app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderSuccessDialog extends AppCompatDialogFragment {

    private static final String KEY_ORDER_NUMBER = "order_number";

    @BindView(R.id.tvOrderNumber)
    TextView tvOrderNumber;

    public static void show(final BaseActivity baseActivity, final String orderNumber){
        final OrderSuccessDialog dialog = new OrderSuccessDialog();
        final Bundle args= new Bundle();
        args.putString(KEY_ORDER_NUMBER, orderNumber);
        dialog.setArguments(args);
        dialog.show(baseActivity.getSupportFragmentManager(), OrderSuccessDialog.class.getName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light_Dialog_Alert);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.dialog_order_success, container, false);
        ButterKnife.bind(this, rootView);
        setCancelable(false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle args = getArguments();
        if (args == null)
            return;
        tvOrderNumber.setText(args.getString(KEY_ORDER_NUMBER, ""));
    }

    @OnClick(R.id.btnContinueShopping)
    void onContinueShoppingClick(View v){
        final Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity)activity).getRouter().showRoot();
        }
        dismiss();
    }

    @OnClick(R.id.tvOrderNumber)
    void onOrderNumberClick(View v){

    }
}

