package com.foxkiev.app.utils.validation;

import com.foxkiev.app.model.models.FieldId;

/**
 * Created by lipcha on 03.05.18.
 */

public interface OnFailedFieldCallback {
    void onFailed(FieldId fieldId);
}
