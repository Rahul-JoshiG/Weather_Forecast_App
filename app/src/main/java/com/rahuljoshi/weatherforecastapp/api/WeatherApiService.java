package com.rahuljoshi.weatherforecastapp.api;
import com.rahuljoshi.weatherforecastapp.model.currentdatamodel.WeatherApiResponse;
import com.rahuljoshi.weatherforecastapp.model.forecastdatamodel.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("v1/current.json") // Update URL if necessary
    Call<WeatherApiResponse> getCurrentWeather(
            @Query("key") String apiKey,
            @Query("q") String location
    );

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
    );}
