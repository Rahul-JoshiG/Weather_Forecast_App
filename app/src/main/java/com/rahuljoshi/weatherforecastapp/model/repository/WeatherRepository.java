package com.rahuljoshi.weatherforecastapp.model.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.rahuljoshi.weatherforecastapp.BuildConfig;
import com.rahuljoshi.weatherforecastapp.api.WeatherApiService;
import com.rahuljoshi.weatherforecastapp.model.data.forecastmodel.WeatherResponse;
import com.rahuljoshi.weatherforecastapp.model.data.todaymodel.TodayWeatherResponse;
import com.rahuljoshi.weatherforecastapp.utils.Constant;

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

    //fetching today weather all info from the api
    public void fetchTodayWeather(String city, TodayWeatherRepositoryCallBack callBack) {
        Log.d(TAG, "fetchTodayWeather: city = " + city);
        String apiKey = BuildConfig.API_KEY;
        try {
            Call<TodayWeatherResponse> call = weatherApiService.fetchTodayWeather(apiKey, city, Constant.TODAY);

            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(@NonNull Call<TodayWeatherResponse> call, @NonNull Response<TodayWeatherResponse> response) {
                    Log.d(TAG, "onResponse: today weather response code in repository " + response.code());
                    if (response.isSuccessful()) {
                        Log.d(TAG, "onResponse: today weather response body in repository " + response.body());
                        callBack.onSuccess(response.body());
                    } else {
                        Log.d(TAG, "onResponse: response is not successfully"+response.message());
                        callBack.onFailure(response.message());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<TodayWeatherResponse> call, @NonNull Throwable throwable) {
                    Log.d(TAG, "onFailure: on call failure in repository = "+throwable.getMessage()+" \n "+throwable.getMessage());
                    callBack.onFailure(throwable.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d(TAG, "fetchTodayWeather: catching error = "+e.getLocalizedMessage());
            callBack.onFailure(e.getLocalizedMessage());
        }
    }

    //getting the forecast data for next 7 days
    public void getForecastWeatherData(String city, int days, WeatherRepositoryForecastCallBack callBack) {
        Log.d(TAG, "getForecastWeatherData: getting forecast data for " + days + " days");
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
        Log.d(TAG, "getLocation: latAndLong " + latAndLong);
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

    // Callbacks for today weather api response
    public interface TodayWeatherRepositoryCallBack {
        void onSuccess(TodayWeatherResponse todayWeatherResponse);
        void onFailure(String errorMessage);
    }
}

