package com.rahuljoshi.weatherforecastapp.model.data.todaymodel;

import com.google.gson.annotations.SerializedName;

public class Current {
    @SerializedName("feelslike_c")
    private double feelsLikeC;
    @SerializedName("feelslike_f")
    private double feelsLikeF;
    @SerializedName("last_updated_epoch")
    private Long lastUpdatedEpoch;
    @SerializedName("temp_c")
    private double tempC;
    @SerializedName("temp_f")
    private double tempF;
    @SerializedName("wind_kph")
    private double windKph;
    @SerializedName("wind_mph")
    private double windMph;
    @SerializedName("humidity")
    private long humidity;
    @SerializedName("uv")
    private double uv;
    @SerializedName("is_day")
    private long isDay;
    @SerializedName("pressure_in")
    private double pressureIn;
    @SerializedName("pressure_mb")
    private long pressureMb;
    @SerializedName("condition")
    private Condition condition;
    @SerializedName("time_epoch")
    private Long timeEpoch;
    @SerializedName("time")
    private String time;

    public double getFeelsLikeC() {
        return feelsLikeC;
    }

    public double getFeelsLikeF() {
        return feelsLikeF;
    }

    public Long getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    public void setLastUpdatedEpoch(Long value) {
        this.lastUpdatedEpoch = value;
    }

    public double getTempC() {
        return tempC;
    }

    public double getTempF() {
        return tempF;
    }

    public double getWindKph() {
        return windKph;
    }

    public double getWindMph() {
        return windMph;
    }

    public long getHumidity() {
        return humidity;
    }

    public double getUv() {
        return uv;
    }

    public long getIsDay() {
        return isDay;
    }

    public double getPressureIn() {
        return pressureIn;
    }

    public Condition getCondition() {
        return condition;
    }

    public long getPressureMb() {
        return pressureMb;
    }

    public Long getTimeEpoch() {
        return timeEpoch;
    }

    public void setTimeEpoch(Long value) {
        this.timeEpoch = value;
    }

    public String getTime() {
        return time;
    }

}
