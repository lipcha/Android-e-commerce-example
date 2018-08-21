package com.foxkiev.app.utils.validation;

import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 14.05.18.
 */

public class NotNullObjectMandatoryField extends MandatoryField<Object> {

    NotNullObjectMandatoryField(Object field, FieldId fieldId) {
        super(field, fieldId);
    }

    @Override
    public boolean isValid() {
        return getField() != null;
    }
}
