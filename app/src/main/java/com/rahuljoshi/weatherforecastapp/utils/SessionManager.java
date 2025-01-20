package com.rahuljoshi.weatherforecastapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    private static final String TAG = "SessionManager";
    private static SharedPreferences mSharedPreferences;

    private SessionManager() {
        // Private constructor to prevent instantiation
    }

    // Initialize the SharedPreferences
    public static void initPreference(Context context) {
        Log.d(TAG, "initPreference: ");
        mSharedPreferences = context.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    // Save a string value
    public static void putString(String key, String value) {
        Log.d(TAG, "putString: " + key + " = " + value);
        mSharedPreferences.edit().putString(key, value).apply();
    }

    // Retrieve a boolean value
    public static String getString(String key) {
        Log.d(TAG, "getString: " + key);
        return mSharedPreferences.getString(key, Constant.CITY_KEY);
    }

    public static void putBoolean(String key, boolean value) {
        Log.d(TAG, "putBoolean: " + key + " = " + value);
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        Log.d(TAG, "getBoolean: " + key);
        return mSharedPreferences.getBoolean(key, false);
    }

    public static void putUint(String key, String value) {
        Log.d(TAG, "putUint: unit is = " + value);
        mSharedPreferences.edit().putString(Constant.UNIT_NAME, value).apply();
    }

    public static String getUnit(String key) {
        Log.d(TAG, "getMetricUnit: getting unit ");
        return mSharedPreferences.getString(key, Constant.UNIT_NAME);
    }

}
