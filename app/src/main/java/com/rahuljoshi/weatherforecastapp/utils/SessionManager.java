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
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
    }

    // Save a string value
    public static void putString(String key, String value) {
        Log.d(TAG, "putString: " + key + " = " + value);
        if (mSharedPreferences != null) {
            mSharedPreferences.edit().putString(key, value).apply();
        } else {
            Log.e(TAG, "SharedPreferences not initialized");
        }
    }

    // Retrieve a boolean value
    public static String getString(String key) {
        Log.d(TAG, "getString: " + key);
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(key, Constant.CITY_KEY);
        } else {
            Log.e(TAG, "SharedPreferences not initialized");
            return null;
        }
    }

    public static void putBoolean(String key, boolean value){
        Log.d(TAG, "putBoolean: " + key + " = "+ value);
        if (mSharedPreferences != null){
            mSharedPreferences.edit().putBoolean(key, value).apply();
        } else {
            Log.d(TAG, "SharedPreferences not initialized");
        }
    }

    public static boolean getBoolean(String key){
        Log.d(TAG, "getBoolean: " + key);
        if (mSharedPreferences != null){
            return mSharedPreferences.getBoolean(key, false);
        }
        return false;
    }

}
