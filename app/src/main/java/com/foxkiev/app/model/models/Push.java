package com.foxkiev.app.model.models;

import com.google.firebase.messaging.RemoteMessage;

import java.io.Serializable;
import java.util.Map;

public class Push implements Serializable {
    private String sourceId;
    private String sourceType;
    private String message;
    private String title;
    private String createAt;

    public static Push fromRemoteMessage(RemoteMessage remoteMessage) {
        final Push push = new Push();
        final Map<String, String> data = remoteMessage.getData();
        if (data != null){
            push.title = data.get("title");
            push.message = data.get("message");
            push.sourceId = data.get("source_id");
            push.sourceType = data.get("source_type");
            push.createAt = data.get("date_time");
        }
        return push;
    }

    public boolean isValid(){
        return sourceId != null && sourceType != null & message != null;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getCreateAt() {
        return createAt;
    }
}
