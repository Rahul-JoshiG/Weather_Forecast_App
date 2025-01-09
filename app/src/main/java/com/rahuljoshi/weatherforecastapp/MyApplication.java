package com.rahuljoshi.weatherforecastapp;

import android.app.Application;

import com.rahuljoshi.weatherforecastapp.utils.SessionManager;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SessionManager.initPreference(this);
    }
}