package com.rahuljoshi.weatherforecastapp.api;

import com.rahuljoshi.weatherforecastapp.model.data.forecastmodel.WeatherResponse;
import com.rahuljoshi.weatherforecastapp.model.data.todaymodel.TodayWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

    @GET("v1/forecast.json")
    Call<WeatherResponse> getForecastWeather(
            @Query("key") String apiKey,
            @Query("q") String location,
            @Query("days") int days
    );

    @GET("v1/current.json")
    Call<WeatherResponse> getLocation(
            @Query("key") String apiKey,
            @Query("q") String lat_long
    );

    @GET("v1/forecast.json")
    Call<TodayWeatherResponse> fetchTodayWeather(
            @Query("key") String apiKey,
            @Query("q") String location,
            @Query("days") int day
    );

}
