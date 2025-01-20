package com.rahuljoshi.weatherforecastapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahuljoshi.weatherforecastapp.databinding.FragmentWeekBinding;
import com.rahuljoshi.weatherforecastapp.model.data.forecastmodel.ForecastDay;
import com.rahuljoshi.weatherforecastapp.utils.Constant;
import com.rahuljoshi.weatherforecastapp.utils.SessionManager;
import com.rahuljoshi.weatherforecastapp.view.adapters.WeekAdapter;
import com.rahuljoshi.weatherforecastapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeekFragment extends Fragment {
    private static final String TAG = "Week Fragment";

    private FragmentWeekBinding mBinding;
    private WeatherViewModel mWeatherViewModel;
    private WeekAdapter mWeekAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentWeekBinding.inflate(inflater, container, false);
        mWeatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the RecyclerView
        setupRecyclerView();
        observesWeatherForecastData();
        mWeatherViewModel.fetchForecastWeatherData(SessionManager.getString(Constant.CITY_KEY));
    }

    private void setupRecyclerView() {
        // Setting up the RecyclerView
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mWeekAdapter = new WeekAdapter(new ArrayList<>(), null); // Initialize with an empty list
        mBinding.recyclerView.setAdapter(mWeekAdapter); // Set the adapter
    }

    private void observesWeatherForecastData() {
        Log.d(TAG, "observesWeatherForecastData: ");
        mWeatherViewModel.getWeatherForecastData().observe(getViewLifecycleOwner(), response -> {
            Log.d(TAG, "observesWeatherForecastData: response = " + response.toString());

            List<ForecastDay> dailyForecasts = response.getForecast().getForecastday();

            mWeekAdapter.updateData(dailyForecasts, response);
            mBinding.recyclerView.setHasFixedSize(true);
        });

        mWeatherViewModel.getForecastIsLoading().observe(getViewLifecycleOwner(), isLoading->{
            if(isLoading){
                mBinding.progressBar.setVisibility(View.VISIBLE);
            } else{
                mBinding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}