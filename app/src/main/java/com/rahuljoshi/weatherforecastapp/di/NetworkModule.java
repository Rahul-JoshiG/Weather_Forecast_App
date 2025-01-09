package com.rahuljoshi.weatherforecastapp.di;

import com.rahuljoshi.weatherforecastapp.api.WeatherApiService;
import com.rahuljoshi.weatherforecastapp.model.repository.WeatherRepository;
import com.rahuljoshi.weatherforecastapp.utils.Constant;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    // Provide WeatherApiService instance
    @Provides
    public static WeatherApiService provideWeatherApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WeatherApiService.class);
    }

    // Provide WeatherRepository instance
    @Provides
    public static WeatherRepository provideWeatherRepository(WeatherApiService weatherApiService) {
        return new WeatherRepository(weatherApiService);
    }
}