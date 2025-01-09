package com.rahuljoshi.weatherforecastapp.utils;

import javax.inject.Singleton;

@Singleton
public class Constant{

    //to restrict object creation
    private Constant(){

    }

    public static final String BASE_URL = "https://api.weatherapi.com/";
    public static final String PREFERENCE_NAME = "city preference";
    public static final String CITY_KEY = "city name";
    public static final String DARK_MODE = "is dark mode";



}
