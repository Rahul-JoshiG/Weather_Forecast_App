package com.rahuljoshi.weatherforecastapp.model.forecastdatamodel;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "hour")
public class Hour {

    @SerializedName("time_epoch")
    @Expose
    private long time_epoch;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("temp_c")
    @Expose
    private double temp_c;

    @SerializedName("temp_f")
    @Expose
    private double temp_f;

    @SerializedName("wind_kph")
    @Expose
    private double wind_kph;

    public double getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public double getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(double temp_f) {
        this.temp_f = temp_f;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getTime_epoch() {
        return time_epoch;
    }

    public void setTime_epoch(long time_epoch) {
        this.time_epoch = time_epoch;
    }
}