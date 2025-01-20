package com.rahuljoshi.weatherforecastapp.model.data.todaymodel;

import com.google.gson.annotations.SerializedName;

public class Condition {
    @SerializedName("code")
    private long code;
    @SerializedName("icon")
    private String icon;

    @SerializedName("text")
    private String text;

    public long getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }
}
