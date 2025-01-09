package com.rahuljoshi.weatherforecastapp.model.forecastdatamodel;

import java.io.IOException;

public enum Text {
    CLEAR, SUNNY, TEXT_CLEAR;

    public String toValue() {
        switch (this) {
            case CLEAR: return "Clear";
            case SUNNY: return "Sunny";
            case TEXT_CLEAR: return "Clear ";
        }
        return null;
    }

    public static Text forValue(String value) throws IOException {
        if (value.equals("Clear")) return CLEAR;
        if (value.equals("Sunny")) return SUNNY;
        if (value.equals("Clear ")) return TEXT_CLEAR;
        throw new IOException("Cannot deserialize Text");
    }
}
