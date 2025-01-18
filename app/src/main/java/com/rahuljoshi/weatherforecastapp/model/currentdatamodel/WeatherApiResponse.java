package com.rahuljoshi.weatherforecastapp.model.currentdatamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


//test line in rahul branch
public class WeatherApiResponse {

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("current")
    @Expose
    private Current current;

    // Getters and Setters
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}