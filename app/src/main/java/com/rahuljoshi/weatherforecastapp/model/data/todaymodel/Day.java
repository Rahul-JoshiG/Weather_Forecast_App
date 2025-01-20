package com.rahuljoshi.weatherforecastapp.model.data.todaymodel;

import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("uv")
    private Double uv;
    @SerializedName("maxtemp_c")
    private double maxTempC;
    @SerializedName("maxtemp_f")
    private double maxTempF;
    @SerializedName("mintemp_c")
    private double minTempC;
    @SerializedName("minTempF")
    private double minTempF;

    public double getMaxTempC() {
        return maxTempC;
    }

    public double getMaxTempF() {
        return maxTempF;
    }

    public double getMinTempC() {
        return minTempC;
    }

    public double getMinTempF() {
        return minTempF;
    }

    public Double getUv() {
        return uv;
    }
}
