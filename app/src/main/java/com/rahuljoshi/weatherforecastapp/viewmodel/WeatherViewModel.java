package com.rahuljoshi.weatherforecastapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rahuljoshi.weatherforecastapp.model.currentdatamodel.WeatherApiResponse;
import com.rahuljoshi.weatherforecastapp.model.forecastdatamodel.WeatherResponse;
import com.rahuljoshi.weatherforecastapp.model.repository.WeatherRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private final String TAG = "WeatherViewModel";

    private final WeatherRepository
            weatherRepository;

    /**
     * MutableLiveData to hold the weather data and error message
     */
    private final MutableLiveData<WeatherApiResponse> mWeatherData = new MutableLiveData<>();
    private final MutableLiveData<String> mErrorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();

    private final MutableLiveData<WeatherResponse> mWeatherForecastData = new MutableLiveData<>();
    private final MutableLiveData<String> mForecastErrorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mForecastLoading = new MutableLiveData<>();

    private final MutableLiveData<WeatherResponse> mWeatherLocationData = new MutableLiveData<>();
    private final MutableLiveData<String> mLocationErrorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mLocationLoading = new MutableLiveData<>();

    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    // Method to fetch weather data from the repository
    public LiveData<WeatherApiResponse> getWeatherData() {
        return mWeatherData;
    }

    // Method to fetch error which comes while fetching data from the repository
    public LiveData<String> getErrorMessage() {
        return mErrorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    // Method to fetch weather data from the repository
    public LiveData<WeatherResponse> getWeatherForecastData() {
        return mWeatherForecastData;
    }

    // Method to fetch error which comes while fetching data from the repository
    public LiveData<String> getWeatherForecastErrorMessage() {
        return mForecastErrorMessage;
    }

    public LiveData<Boolean> getForecastIsLoading() {
        return mForecastLoading;
    }


    // Method to fetch location data from the repository
    public LiveData<WeatherResponse> getWeatherLocation() {
        return mWeatherLocationData;
    }

    // Method to fetch error which comes while fetching data from the repository
    public LiveData<String> getLocationErrorMessage() {
        return mLocationErrorMessage;
    }

    public LiveData<Boolean> getLocationIsLoading() {
        return mLocationLoading;
    }

    // Method to fetch weather data from the repository
    public void fetchWeatherData(String city) {
        Log.d(TAG, "fetchWeatherData: from today fragment in view model city = "+city);
        mIsLoading.postValue(true);
        weatherRepository.getCurrentWeatherData(city, new WeatherRepository.WeatherRepositoryCallBack() {
            @Override
            public void onSuccess(WeatherApiResponse response) {
                Log.d(TAG, "onSuccess: response in view model = " + response.toString());
                mWeatherData.postValue(response);
                mIsLoading.postValue(false);
            }

            @Override
            public void onFailure(String error) {
                Log.d(TAG, "onFailure: failed in view model = " + error);
                mErrorMessage.postValue(error);
                mIsLoading.postValue(false);
            }
        });
    }


    // Method to fetch weather data from the repository
    public void fetchForecastWeatherData(String city, int days) {
        mForecastLoading.postValue(true);
        weatherRepository.getForecastWeatherData(city, days, new WeatherRepository.WeatherRepositoryForecastCallBack() {
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
        weatherRepository.getLocation(latAndLong, new WeatherRepository.WeatherRepositoryLocationCallBack(){
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

}
