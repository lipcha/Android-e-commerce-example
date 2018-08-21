package com.foxkiev.app.ui.custom_views;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.foxkiev.app.model.models.PaymentMethod;

import java.util.List;

public class PaymentMethodsRadioGroup extends RadioGroup {

    public PaymentMethodsRadioGroup(Context context) {
        super(context);
    }

    public PaymentMethodsRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods){
        if (paymentMethods == null || paymentMethods.isEmpty())
            return;
        removeAllViews();
        for (PaymentMethod paymentMethod : paymentMethods){
            final AppCompatRadioButton radioButton = new AppCompatRadioButton(getContext());
            radioButton.setText(paymentMethod.getTitle());
            radioButton.setTag(paymentMethod);
            addView(radioButton);

            if (getCheckedRadioButtonId() == -1)
                check(radioButton.getId());
        }
    }

    public PaymentMethod getSelectedPaymentMethod(){
        final int selectedId = getCheckedRadioButtonId();
        final AppCompatRadioButton appCompatRadioButton = findViewById(selectedId);
        if (appCompatRadioButton == null)
            return null;
        final Object o = appCompatRadioButton.getTag();
        return  (o != null && o instanceof PaymentMethod) ? (PaymentMethod) o : null;
    }
}
