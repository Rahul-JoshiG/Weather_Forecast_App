package com.rahuljoshi.weatherforecastapp.model.repository;

import android.util.Log;
import androidx.annotation.NonNull;
import com.rahuljoshi.weatherforecastapp.BuildConfig;
import com.rahuljoshi.weatherforecastapp.api.WeatherApiService;
import com.rahuljoshi.weatherforecastapp.model.currentdatamodel.WeatherApiResponse;
import com.rahuljoshi.weatherforecastapp.model.forecastdatamodel.WeatherResponse;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private final String TAG = "WeatherRepository";
    private final WeatherApiService weatherApiService;

    @Inject
    public WeatherRepository(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    //getting the current weather data
    public void getCurrentWeatherData(String city, WeatherRepositoryCallBack callBack) {
        Log.d(TAG, "getCurrentWeatherData: city in repository class is = "+city);
        final String apiKey = BuildConfig.API_KEY;

        Call<WeatherApiResponse> responseCall = weatherApiService.getCurrentWeather(apiKey, city);

        responseCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<WeatherApiResponse> call, @NonNull Response<WeatherApiResponse> response) {
                Log.d(TAG, "onResponse: response code = " + response.code());
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailure("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherApiResponse> call, @NonNull Throwable throwable) {
                Log.d(TAG, "onFailure: failed to load data : " + throwable.getCause());
                callBack.onFailure(throwable.getMessage());

            }
        });
    }


    //getting the forecast data for next 7 days
    public void getForecastWeatherData(String city, int days, WeatherRepositoryForecastCallBack callBack) {
        Log.d(TAG, "getForecastWeatherData: getting forecast data for "+days+" days");
        final String apiKey = BuildConfig.API_KEY;

        Call<WeatherResponse> responseCall = weatherApiService.getForecastWeather(apiKey, city, days);

        responseCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                Log.d(TAG, "onResponse: response code = " + response.code());
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailure("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable throwable) {
                Log.d(TAG, "onFailure: failure = " + throwable.getMessage());
                callBack.onFailure(throwable.getMessage());
            }
        });
    }

    //getting the location using latitude and longitude
    public void getLocation(String latAndLong, WeatherRepositoryLocationCallBack callBack) {
        Log.d(TAG, "getLocation: latAndLong "+latAndLong);
        final String apiKey = BuildConfig.API_KEY;

        Call<WeatherResponse> responseCall = weatherApiService.getLocation(apiKey, latAndLong);

        responseCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                Log.d(TAG, "onResponse: response code = " + response.code());
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailure("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable throwable) {
                Log.d(TAG, "onFailure: failure = " + throwable.getMessage());
                callBack.onFailure(throwable.getMessage());
            }
        });
    }

    // Callbacks for current API responses
    public interface WeatherRepositoryCallBack {
        void onSuccess(WeatherApiResponse weatherApiResponse);
        void onFailure(String errorMessage);
    }

    // Callbacks for forecast API responses
    public interface WeatherRepositoryForecastCallBack {
        void onSuccess(WeatherResponse weatherResponse);
        void onFailure(String errorMessage);
    }

    // Callbacks for location API responses
    public interface WeatherRepositoryLocationCallBack {
        void onSuccess(WeatherResponse weatherResponse);
        void onFailure(String errorMessage);
    }
}

