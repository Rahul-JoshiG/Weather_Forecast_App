package com.rahuljoshi.weatherforecastapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rahuljoshi.weatherforecastapp.model.data.forecastmodel.WeatherResponse;
import com.rahuljoshi.weatherforecastapp.model.data.todaymodel.TodayWeatherResponse;
import com.rahuljoshi.weatherforecastapp.model.repository.WeatherRepository;
import com.rahuljoshi.weatherforecastapp.utils.Constant;
import com.rahuljoshi.weatherforecastapp.utils.SessionManager;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final String TAG = "WeatherViewModel";

    private final WeatherRepository weatherRepository;

    private final MutableLiveData<WeatherResponse> mWeatherForecastData = new MutableLiveData<>();
    private final MutableLiveData<String> mForecastErrorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mForecastLoading = new MutableLiveData<>();

    private final MutableLiveData<WeatherResponse> mWeatherLocationData = new MutableLiveData<>();
    private final MutableLiveData<String> mLocationErrorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLocationLoading = new MutableLiveData<>();

    private final MutableLiveData<String> mLocation = new MutableLiveData<>();

    public MutableLiveData<String> getLocation() {
        return mLocation; // Only return the LiveData
    }

    // Method to update the location value
    public void updateLocation(String city) {
        Log.d(TAG, "updateLocation: updating location");
        SessionManager.putString(Constant.CITY_KEY, city);
        mLocation.postValue(SessionManager.getString(Constant.CITY_KEY));
    }


    //mutable live data to hold the today weather info
    private final MutableLiveData<TodayWeatherResponse> mTodayWeatherData = new MutableLiveData<>();
    private final MutableLiveData<String> mTodayWeatherFailure = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mTodayWeatherLoading = new MutableLiveData<>();



    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public LiveData<WeatherResponse> getWeatherForecastData() {
        return mWeatherForecastData;
    }
    public LiveData<Boolean> getForecastIsLoading() {
        return mForecastLoading;
    }


    // Method to fetch location data from the repository
    public LiveData<WeatherResponse> getWeatherLocation() {
        return mWeatherLocationData;
    }


    //method to fetch today weather response into live data
    public LiveData<TodayWeatherResponse> getTodayWeatherResponse() {
        return mTodayWeatherData;
    }

    public LiveData<Boolean> getTodayWeatherLoading(){
        return mTodayWeatherLoading;
    }


    // Method to fetch weather data from the repository
    public void fetchForecastWeatherData(String city) {
        mForecastLoading.postValue(true);
        weatherRepository.getForecastWeatherData(city, Constant.WEEK, new WeatherRepository.WeatherRepositoryForecastCallBack() {
            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                Log.d(TAG, "onSuccess: weather response " + weatherResponse.toString());
                mWeatherForecastData.postValue(weatherResponse);
                mForecastLoading.postValue(false);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d(TAG, "onFailure: error Message = " + errorMessage);
                mForecastErrorMessage.postValue(errorMessage);
                mForecastLoading.postValue(false);
            }
        });
    }

    // Method to fetch weather data from the repository
    public void fetchLocation(String latAndLong) {
        mForecastLoading.postValue(true);
        weatherRepository.getLocation(latAndLong, new WeatherRepository.WeatherRepositoryLocationCallBack() {
            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                Log.d(TAG, "onSuccess: weather response " + weatherResponse.toString());
                mWeatherLocationData.postValue(weatherResponse);
                mLocationLoading.postValue(false);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d(TAG, "onFailure: error Message = " + errorMessage);
                mLocationErrorMessage.postValue(errorMessage);
                mLocationLoading.postValue(false);
            }
        });
    }

    //method to fetch today weather response
    public void getTodayWeatherResponse(String city) {
        Log.d(TAG, "getTodayWeatherResponse: ");
        mTodayWeatherLoading.postValue(true);
        weatherRepository.fetchTodayWeather(city, new WeatherRepository.TodayWeatherRepositoryCallBack() {
            @Override
            public void onSuccess(TodayWeatherResponse todayWeatherResponse) {
                Log.d(TAG, "onSuccess: ");
                mTodayWeatherData.postValue(todayWeatherResponse);
                mTodayWeatherLoading.postValue(false);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d(TAG, "onFailure: ");
                mTodayWeatherFailure.postValue(errorMessage);
                mTodayWeatherLoading.postValue(false);
            }
        });
    }
}