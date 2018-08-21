package com.foxkiev.app.model.models.request;

public class ChangePasswordWrapper {

    private final String currentPassword;

    private final String newPassword;

    public ChangePasswordWrapper(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
