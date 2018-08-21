package com.foxkiev.app.utils.validation;

import com.foxkiev.app.model.models.FieldId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipcha on 03.05.18.
 */

public class Validator {
    private final List<MandatoryField> mMandatoryFields = new ArrayList<>();

    private Validator() {
    }

    public static Validator create(){
        return new Validator();
    }

    public Validator addTextField(final String field, final FieldId fieldId){
        mMandatoryFields.add(new TextMandatoryField(field, fieldId));
        return this;
    }

    public Validator addNotEmptyField(final String field, final FieldId fieldId){
        mMandatoryFields.add(new NotEmptyMandatoryField(field, fieldId));
        return this;
    }

    public Validator addEmailField(final String email, final FieldId fieldId){
        mMandatoryFields.add(new EmailMandatoryField(email, fieldId));
        return this;
    }

    public Validator addPasswordField(final String password, FieldId fieldId){
        mMandatoryFields.add(new PasswordMandatoryField(password, fieldId));
        return this;
    }

    public Validator addConfirmPasswordField(final String password, final String confirmPassword, FieldId fieldId){
        mMandatoryFields.add(new ConfirmPasswordMandatoryField(password, confirmPassword, fieldId));
        return this;
    }

    public Validator addPhoneNumberField(final String phoneNumber, FieldId fieldId){
        mMandatoryFields.add(new PhoneNumberMandatoryField(phoneNumber, fieldId));
        return this;
    }

    public Validator addNotNullIntField(final int field, final FieldId fieldId){
        mMandatoryFields.add(new NotNullIntMandatoryField(field, fieldId));
        return this;
    }
    public Validator addNotNullObject(final Object o, FieldId fieldId){
        mMandatoryFields.add(new NotNullObjectMandatoryField(o, fieldId));
        return this;
    }

    public void validate(final OnValidSuccessCallback onValidSuccess, final OnFailedFieldCallback onFailedFieldCallback){
        if (onValidSuccess == null)
            return;
        if (mMandatoryFields.isEmpty())
            onValidSuccess.onFieldsValid();
        for (MandatoryField mandatoryField : mMandatoryFields){
            if (!mandatoryField.isValid()){
                onFailedFieldCallback.onFailed(mandatoryField.getFieldId());
                return;
            }
        }

        onValidSuccess.onFieldsValid();
    }

}
