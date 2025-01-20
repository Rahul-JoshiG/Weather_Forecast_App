package com.rahuljoshi.weatherforecastapp.model.data.todaymodel;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("localtime")
    private String localTime;
    @SerializedName("country")
    private String country;
    @SerializedName("localtime_epoch")
    private long localTimeEpoch;
    @SerializedName("name")
    private String name;
    @SerializedName("lon")
    private double lon;
    @SerializedName("region")
    private String region;
    @SerializedName("lat")
    private double lat;
    @SerializedName("tz_id")
    private String tzid;

    public String getCountry() {
        return country;
    }

    public double getLat() {
        return lat;
    }

    public String getLocalTime() {
        return localTime;
    }

    public long getLocalTimeEpoch() {
        return localTimeEpoch;
    }

    public double getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getTzid() {
        return tzid;
    }
}
