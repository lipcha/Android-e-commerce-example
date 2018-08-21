package com.foxkiev.app.utils.validation;

import android.text.TextUtils;

import com.foxkiev.app.model.models.FieldId;

import java.util.regex.Pattern;

/**
 * Created by lipcha on 03.05.18.
 */

class PhoneNumberMandatoryField extends MandatoryField<String>{

    PhoneNumberMandatoryField(String field, FieldId fieldId) {
        super(field, fieldId);
    }

    @Override
    public boolean isValid() {
        return !TextUtils.isEmpty(getField()) && Pattern.compile("[+]?[0-9]{10,12}").matcher(getField()).matches();
    }
}

