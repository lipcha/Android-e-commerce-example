package com.foxkiev.app.utils.validation;

import android.text.TextUtils;

import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 03.05.18.
 */

class TextMandatoryField extends MandatoryField<String>{

    TextMandatoryField(String field, FieldId fieldId) {
        super(field, fieldId);
    }

    @Override
    public boolean isValid() {
        return !TextUtils.isEmpty(getField());
    }
}
