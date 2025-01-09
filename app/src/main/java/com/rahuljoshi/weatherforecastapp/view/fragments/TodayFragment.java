package com.rahuljoshi.weatherforecastapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.rahuljoshi.weatherforecastapp.databinding.FragmentTodayBinding;
import com.rahuljoshi.weatherforecastapp.utils.Constant;
import com.rahuljoshi.weatherforecastapp.utils.SessionManager;
import com.rahuljoshi.weatherforecastapp.viewmodel.WeatherViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TodayFragment extends Fragment {
    private static final String TAG = "TodayFragment";
    private FragmentTodayBinding mBinding;
    private WeatherViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentTodayBinding.inflate(inflater, container, false);
        // Use ViewModelProvider to get the ViewModel instance
        mViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        return mBinding.getRoot();
    }

    private static final String ARG_CITY_NAME = "city_name";
    private String cityName;

    // Factory method to create a new instance of TodayFragment
    public static TodayFragment newInstance(String cityName) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY_NAME, cityName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cityName = getArguments().getString(ARG_CITY_NAME);
            SessionManager.putString(Constant.CITY_KEY, cityName);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        observeWeatherData();
        getCityNameFromLocation();
    }

    // Observe weather data and update UI accordingly
    private void observeWeatherData() {
        Log.d(TAG, "observeWeatherData: Observing weather data in fragment");

        // Observe loading state
        mViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                mBinding.progressBar.setVisibility(View.VISIBLE);
            } else {
                mBinding.progressBar.setVisibility(View.GONE);
            }
        });

        // Observe weather data
        mViewModel.getWeatherData().observe(getViewLifecycleOwner(), response -> {
            if (response != null && response.getCurrent() != null) {

                mBinding.cityName.setText(SessionManager.getString(Constant.CITY_KEY));
                mBinding.currentTemp.setText(String.format("%s °C", response.getCurrent().getTempC()));
                mBinding.currentCondition.setText(response.getCurrent().getCondition().getText());

                Glide.with(requireContext())
                        .load("https:" + response.getCurrent().getCondition().getIcon())
                        .into(mBinding.appCompatImageView);

                mBinding.humidityValue.setText(String.valueOf(response.getCurrent().getHumidity()));
                mBinding.pressureValue.setText(String.format("%s mbar", response.getCurrent().getPressureMb()));
                mBinding.uvValue.setText(String.valueOf(response.getCurrent().getUv()));
                mBinding.cloudValue.setText(String.valueOf(response.getCurrent().getCloud()));
                mBinding.realFeelValue.setText(String.format("%s °C", response.getCurrent().getFeelslikeC()));
            }
        });

        // Observe error messages
        mViewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show();
            }
        });
    }

    // Fetch weather data based on the city name
    private void getCityNameFromLocation() {
        mViewModel.getLocationIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                mBinding.progressBar.setVisibility(View.VISIBLE);
            } else {
                mBinding.progressBar.setVisibility(View.GONE);
            }
        });

        mViewModel.fetchWeatherData(SessionManager.getString(Constant.CITY_KEY));
    }
}