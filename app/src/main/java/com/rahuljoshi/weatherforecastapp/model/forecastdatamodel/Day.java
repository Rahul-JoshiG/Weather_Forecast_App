package com.rahuljoshi.weatherforecastapp.model.forecastdatamodel;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "day")
public class Day {
    @SerializedName("maxtemp_c")
    @Expose
    private double maxTempC;

    @SerializedName("maxtemp_f")
    @Expose
    private double maxTempF;

    public double getMaxTempC() {
        return maxTempC;
    }

    public void setMaxTempC(double maxTempC) {
        this.maxTempC = maxTempC;
    }

    public double getMaxTempF() {
        return maxTempF;
    }

    public void setMaxTempF(double maxTempF) {
        this.maxTempF = maxTempF;
    }
}