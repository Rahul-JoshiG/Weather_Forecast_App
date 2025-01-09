package com.rahuljoshi.weatherforecastapp.model.currentdatamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Current {

    @SerializedName("last_updated_epoch")
    @Expose
    private long lastUpdatedEpoch;

    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;

    @SerializedName("temp_c")
    @Expose
    private float tempC;

    @SerializedName("temp_f")
    @Expose
    private float tempF;

    @SerializedName("is_day")
    @Expose
    private int isDay;

    @SerializedName("condition")
    @Expose
    private Condition condition;

    @SerializedName("wind_mph")
    @Expose
    private float windMph;

    @SerializedName("wind_kph")
    @Expose
    private float windKph;

    @SerializedName("wind_degree")
    @Expose
    private int windDegree;

    @SerializedName("wind_dir")
    @Expose
    private String windDir;

    @SerializedName("pressure_mb")
    @Expose
    private float pressureMb;

    @SerializedName("pressure_in")
    @Expose
    private float pressureIn;

    @SerializedName("precip_mm")
    @Expose
    private float precipMm;

    @SerializedName("precip_in")
    @Expose
    private float precipIn;

    @SerializedName("humidity")
    @Expose
    private int humidity;

    @SerializedName("cloud")
    @Expose
    private int cloud;

    @SerializedName("feelslike_c")
    @Expose
    private float feelslikeC;

    @SerializedName("feelslike_f")
    @Expose
    private float feelslikeF;

    @SerializedName("windchill_c")
    @Expose
    private float windchillC;

    @SerializedName("windchill_f")
    @Expose
    private float windchillF;

    @SerializedName("heatindex_c")
    @Expose
    private float heatindexC;

    @SerializedName("heatindex_f")
    @Expose
    private float heatindexF;

    @SerializedName("dewpoint_c")
    @Expose
    private float dewpointC;

    @SerializedName("dewpoint_f")
    @Expose
    private float dewpointF;

    @SerializedName("vis_km")
    @Expose
    private float visKm;

    @SerializedName("vis_miles")
    @Expose
    private float visMiles;

    @SerializedName("uv")
    @Expose
    private float uv;

    @SerializedName("gust_mph")
    @Expose
    private float gustMph;

    @SerializedName("gust_kph")
    @Expose
    private float gustKph;

    // Getters and Setters
    public long getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    public void setLastUpdatedEpoch(long lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public float getTempC() {
        return tempC;
    }

    public void setTempC(float tempC) {
        this.tempC = tempC;
    }

    public float getTempF() {
        return tempF;
    }

    public void setTempF(float tempF) {
        this.tempF = tempF;
    }

    public int getIsDay() {
        return isDay;
    }

    public void setIsDay(int isDay) {
        this.isDay = isDay;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public float getWindMph() {
        return windMph;
    }

    public void setWindMph(float windMph) {
        this.windMph = windMph;
    }

    public float getWindKph() {
        return windKph;
    }

    public void setWindKph(float windKph) {
        this.windKph = windKph;
    }

    public int getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(int windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public float getPressureMb() {
        return pressureMb;
    }

    public void setPressureMb(float pressureMb) {
        this.pressureMb = pressureMb;
    }

    public float getPressureIn() {
        return pressureIn;
    }

    public void setPressureIn(float pressureIn) {
        this.pressureIn = pressureIn;
    }

    public float getPrecipMm() {
        return precipMm;
    }

    public void setPrecipMm(float precipMm) {
        this.precipMm = precipMm;
    }

    public float getPrecipIn() {
        return precipIn;
    }

    public void setPrecipIn(float precipIn) {
        this.precipIn = precipIn;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }

    public float getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(float feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public float getFeelslikeF() {
        return feelslikeF;
    }

    public void setFeelslikeF(float feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public float getWindchillC() {
        return windchillC;
    }

    public void setWindchillC(float windchillC) {
        this.windchillC = windchillC;
    }

    public float getWindchillF() {
        return windchillF;
    }

    public void setWindchillF(float windchillF) {
        this.windchillF = windchillF;
    }

    public float getHeatindexC() {
        return heatindexC;
    }

    public void setHeatindexC(float heatindexC) {
        this.heatindexC = heatindexC;
    }

    public float getHeatindexF() {
        return heatindexF;
    }

    public void setHeatindexF(float heatindexF) {
        this.heatindexF = heatindexF;
    }

    public float getDewpointC() {
        return dewpointC;
    }

    public void setDewpointC(float dewpointC) {
        this.dewpointC = dewpointC;
    }

    public float getDewpointF() {
        return dewpointF;
    }

    public void setDewpointF(float dewpointF) {
        this.dewpointF = dewpointF;
    }

    public float getVisKm() {
        return visKm;
    }

    public void setVisKm(float visKm) {
        this.visKm = visKm;
    }

    public float getVisMiles() {
        return visMiles;
    }

    public void setVisMiles(float visMiles) {
        this.visMiles = visMiles;
    }

    public float getUv() {
        return uv;
    }

    public void setUv(float uv) {
        this.uv = uv;
    }

    public float getGustMph() {
        return gustMph;
    }

    public void setGustMph(float gustMph) {
        this.gustMph = gustMph;
    }

    public float getGustKph() {
        return gustKph;
    }

    public void setGustKph(float gustKph) {
        this.gustKph = gustKph;
    }
}