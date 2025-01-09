package com.rahuljoshi.weatherforecastapp.model.forecastdatamodel;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "current")
public class Current {

    @SerializedName("last_updated_epoch")
    @Expose
    private long last_updated_epoch;

    @SerializedName("last_updated")
    @Expose
    private String last_updated;

    @SerializedName("temp_c")
    @Expose
    private double temp_c;

    @SerializedName("temp_f")
    @Expose
    private double temp_f;

    @SerializedName("is_day")
    @Expose
    private int is_day;

    @SerializedName("condition")
    @Expose
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getIs_day() {
        return is_day;
    }

    public void setIs_day(int is_day) {
        this.is_day = is_day;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public long getLast_updated_epoch() {
        return last_updated_epoch;
    }

    public void setLast_updated_epoch(long last_updated_epoch) {
        this.last_updated_epoch = last_updated_epoch;
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
}
