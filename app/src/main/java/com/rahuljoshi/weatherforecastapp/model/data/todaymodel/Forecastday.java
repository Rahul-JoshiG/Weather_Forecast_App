package com.rahuljoshi.weatherforecastapp.model.data.todaymodel;

import com.google.gson.annotations.SerializedName;

public class Forecastday {
    @SerializedName("date")
    private String date;

    @SerializedName("astro")
    private Astro astro;

    @SerializedName("date_epoch")
    private long dateEpoch;

    @SerializedName("day")
    private Day day;

    public String getDate() {
        return date;
    }

    public Astro getAstro() {
        return astro;
    }

    public long getDateEpoch() {
        return dateEpoch;
    }

    public Day getDay() {
        return day;
    }

}
