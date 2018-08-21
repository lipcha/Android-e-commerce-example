package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.foxkiev.app.model.models.ShippingMethod;

import java.util.List;

public class ShippingMethodsRadioGroup extends RadioGroup {

    public ShippingMethodsRadioGroup(Context context) {
        super(context);
    }

    public ShippingMethodsRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setShippingMethods(List<ShippingMethod> shippingMethods){
        if (shippingMethods == null || shippingMethods.isEmpty())
            return;
        removeAllViews();
        for (ShippingMethod shippingMethod : shippingMethods){
            final AppCompatRadioButton radioButton = new AppCompatRadioButton(getContext());
            final String shippingMethodTitle = shippingMethod.getMethodTitle() + "  " + shippingMethod.getCarrierTitle();
            radioButton.setText(shippingMethodTitle);
            radioButton.setTag(shippingMethod);
            addView(radioButton);

            if (getCheckedRadioButtonId() == -1)
                check(radioButton.getId());
        }
    }

    public ShippingMethod getSelectedShippingMethod(){
        final int selectedId = getCheckedRadioButtonId();
        final AppCompatRadioButton appCompatRadioButton = findViewById(selectedId);
        if (appCompatRadioButton == null)
            return null;
        final Object o = appCompatRadioButton.getTag();
        return  (o != null && o instanceof ShippingMethod) ? (ShippingMethod) o : null;
    }
}
