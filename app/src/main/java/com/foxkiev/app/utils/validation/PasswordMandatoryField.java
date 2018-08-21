package com.foxkiev.app.utils.validation;

import android.text.TextUtils;

import com.foxkiev.app.Constants;
import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 03.05.18.
 */

class PasswordMandatoryField extends MandatoryField<String>{

    PasswordMandatoryField(String field, FieldId fieldId) {
        super(field, fieldId);
    }

    @Override
    public boolean isValid() {
        return !TextUtils.isEmpty(getField()) && getField().length() >= Constants.PASSWORD_MIN_LENGTH;
    }
}
