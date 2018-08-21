package com.foxkiev.app.utils.validation;

import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 03.05.18.
 */

class NotNullIntMandatoryField extends MandatoryField<Integer> {


    NotNullIntMandatoryField(Integer field, FieldId fieldId) {
        super(field, fieldId);
    }

    @Override
    public boolean isValid() {
        return getField() != null && getField() != 0;
    }
}
