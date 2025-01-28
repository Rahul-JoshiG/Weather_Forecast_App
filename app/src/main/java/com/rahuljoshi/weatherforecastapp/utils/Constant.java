package com.rahuljoshi.weatherforecastapp.utils;

import javax.inject.Singleton;

@Singleton
public class Constant{

    //to restrict object creation
    private Constant(){

    }

    public static final String BASE_URL = "https://api.weatherapi.com/";
    public static final String PREFERENCE_NAME = "city preference";
    public static final String CITY_KEY = "__";
    public static final String DARK_MODE = "is dark mode";
    public static final int TODAY = 1;
    public static final int WEEK = 7;
    public static final String UNIT_NAME = "Metric";


}
