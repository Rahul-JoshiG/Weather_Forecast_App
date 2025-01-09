package com.rahuljoshi.weatherforecastapp.model.forecastdatamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Condition {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("code")
    @Expose
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}