package com.rahuljoshi.weatherforecastapp.model.forecastdatamodel;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "forecast")
public class Forecast {
    @SerializedName("forecastday")
    @Expose
    private List<ForecastDay> forecastday;

    // Getters and setters
    public List<ForecastDay> getForecastday() {
        return forecastday;
    }
    public void setForecastday(List<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}
