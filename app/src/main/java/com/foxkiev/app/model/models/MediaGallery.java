package com.foxkiev.app.model.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lipcha on 28.02.18.
 */

public class MediaGallery implements Parcelable{

    private int id;
    @SerializedName("media_type")
    private String mediaType;
    private String label;
    private int position;
    private boolean disabled;
    private List<String> types;
    private String file;

    public int getId() {
        return id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getLabel() {
        return label;
    }

    public int getPosition() {
        return position;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public List<String> getTypes() {
        return types;
    }

    public String getFile() {
        return file;
    }

    protected MediaGallery(Parcel in) {
        id = in.readInt();
        mediaType = in.readString();
        label = in.readString();
        position = in.readInt();
        disabled = in.readByte() != 0;
        types = in.createStringArrayList();
        file = in.readString();
    }

    public static final Creator<MediaGallery> CREATOR = new Creator<MediaGallery>() {
        @Override
        public MediaGallery createFromParcel(Parcel in) {
            return new MediaGallery(in);
        }

        @Override
        public MediaGallery[] newArray(int size) {
            return new MediaGallery[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(mediaType);
        dest.writeString(label);
        dest.writeInt(position);
        dest.writeByte((byte) (disabled ? 1 : 0));
        dest.writeStringList(types);
        dest.writeString(file);
    }
}
