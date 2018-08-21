package com.foxkiev.app.utils.validation;

import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 03.05.18.
 */

abstract class MandatoryField<F> {

    private F mField;
    private FieldId mFieldId;

    MandatoryField(F field, FieldId fieldId) {
        this.mField = field;
        this.mFieldId = fieldId;
    }

    public abstract boolean isValid();

    public F getField() {
        return mField;
    }

    public FieldId getFieldId() {
        return mFieldId;
    }
}
