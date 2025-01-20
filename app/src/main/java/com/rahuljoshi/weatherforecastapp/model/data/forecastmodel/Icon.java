package com.rahuljoshi.weatherforecastapp.model.data.forecastmodel;

import java.io.IOException;

// Icon.java
public enum Icon {
    CDN_WEATHERAPI_com_WEATHER_64_X64_DAY_113_png, CDN_WEATHERAPI_com_WEATHER_64_X64_NIGHT_113_png;

    public String toValue() {
        switch (this) {
            case CDN_WEATHERAPI_com_WEATHER_64_X64_DAY_113_png: return "//cdn.weatherapi.com/weather/64x64/day/113.png";
            case CDN_WEATHERAPI_com_WEATHER_64_X64_NIGHT_113_png: return "//cdn.weatherapi.com/weather/64x64/night/113.png";
        }
        return null;
    }

    public static Icon forValue(String value) throws IOException {
        if (value.equals("//cdn.weatherapi.com/weather/64x64/day/113.png")) return CDN_WEATHERAPI_com_WEATHER_64_X64_DAY_113_png;
        if (value.equals("//cdn.weatherapi.com/weather/64x64/night/113.png")) return CDN_WEATHERAPI_com_WEATHER_64_X64_NIGHT_113_png;
        throw new IOException("Cannot deserialize Icon");
    }
}
