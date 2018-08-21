package com.foxkiev.app.model.models.request;

/**
 * Created by lipcha on 07.05.18.
 */

public class EmailWrapper {

    private final String email;
    private final String template;
    private final int websiteId;

    public EmailWrapper(String email) {
        this.email = email;
        template = "email_reset";
        websiteId = 1;
    }
}
