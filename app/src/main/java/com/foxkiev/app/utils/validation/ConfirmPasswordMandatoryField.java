package com.foxkiev.app.utils.validation;

import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 03.05.18.
 */

class ConfirmPasswordMandatoryField extends MandatoryField<String> {

    private final String mConfirmField;

    ConfirmPasswordMandatoryField(String field, String confirmField, FieldId fieldId) {
        super(field, fieldId);
        mConfirmField = confirmField;
    }

    @Override
    public boolean isValid() {
        return mConfirmField.equals(getField());
    }
}

