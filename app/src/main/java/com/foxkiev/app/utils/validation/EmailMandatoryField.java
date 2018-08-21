package com.foxkiev.app.utils.validation;

import android.util.Patterns;

import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 03.05.18.
 */

class EmailMandatoryField extends MandatoryField<String> {


    EmailMandatoryField(String field, FieldId fieldId) {
        super(field, fieldId);
    }

    @Override
    public boolean isValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getField()).matches();
    }
}
