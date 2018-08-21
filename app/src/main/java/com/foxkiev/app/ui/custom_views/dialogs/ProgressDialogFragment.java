package com.foxkiev.app.ui.custom_views.dialogs;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by lipcha on 03.05.18.
 */

public class ProgressDialogFragment extends DialogFragment {

    private static final String TAG = ProgressDialogFragment.class.getName();

    public static void show(final FragmentManager fragmentManager){
        final ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        progressDialogFragment.show(fragmentManager, TAG);
    }

    public static void hide(final FragmentManager fragmentManager){
        if (fragmentManager == null)
            return;
        final Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment != null && fragment instanceof DialogFragment)
            ((DialogFragment)fragment).dismiss();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
//        dialog.setTitle("Please wait");
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dialog;
    }
}