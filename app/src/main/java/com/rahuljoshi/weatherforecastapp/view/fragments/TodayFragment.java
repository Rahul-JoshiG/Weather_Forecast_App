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

import com.rahuljoshi.weatherforecastapp.databinding.FragmentTodayBinding;
import com.rahuljoshi.weatherforecastapp.model.data.todaymodel.TodayWeatherResponse;
import com.rahuljoshi.weatherforecastapp.utils.Constant;
import com.rahuljoshi.weatherforecastapp.utils.SessionManager;
import com.rahuljoshi.weatherforecastapp.utils.WeatherIconMapper;
import com.rahuljoshi.weatherforecastapp.viewmodel.WeatherViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TodayFragment extends Fragment {
    private static final String TAG = "TodayFragment";
    private FragmentTodayBinding mBinding;
    private WeatherViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: today fragment");
        mBinding = FragmentTodayBinding.inflate(inflater, container, false);
        // Use ViewModelProvider to get the ViewModel instance
        mViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        getTodayWeatherData();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: today fragment");

        observeWeatherData();
    }

    private void observeWeatherData() {
        Log.d(TAG, "observeWeatherData: ");

        mViewModel.getTodayWeatherLoading().observe(getViewLifecycleOwner(), isLoading->{
            if(isLoading){
                mBinding.progressBar.setVisibility(View.VISIBLE);
            }else{
                mBinding.progressBar.setVisibility(View.GONE);
            }
        });

        mViewModel.getTodayWeatherResponse().observe(getViewLifecycleOwner(), response -> {
            if (response != null) {
                String currentUnit = SessionManager.getUnit(Constant.UNIT_NAME);
                updateUi(response, currentUnit);
            }
        });
    }


    private void updateUi(TodayWeatherResponse response, String unit) {
        Log.d(TAG, "updateUi: ");

        String currentCity = SessionManager.getString(Constant.CITY_KEY);
        mBinding.currentLocation.setText(currentCity);

        Long currentTimeEpoch = response.getCurrent().getLastUpdatedEpoch();
        Date date = new Date(currentTimeEpoch * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd", Locale.ENGLISH);
        String currentDate = formatter.format(date);
        mBinding.currentDateTime.setText(currentDate);

        String condition = response.getCurrent().getCondition().getText();
        mBinding.conditionName.setText(condition);

        String humidity = String.valueOf(response.getCurrent().getHumidity());
        mBinding.humidityValue.setText(humidity + "%");

        String sunrise = response.getForecast().getForecastday().get(0).getAstro().getSunrise();
        mBinding.sunriseValue.setText(sunrise);

        String sunset = response.getForecast().getForecastday().get(0).getAstro().getSunset();
        mBinding.sunsetValue.setText(sunset);

        Long code = response.getCurrent().getCondition().getCode();
        boolean isDay = response.getCurrent().getIsDay() == 1;
        showConditionIcon(code, isDay);

        if (unit.equals(Constant.UNIT_NAME)) {
            int currentTemp = (int) response.getCurrent().getTempC();
            mBinding.currentTemp.setText(String.format("%s°C", currentTemp));

            String pressureMbr = String.valueOf(response.getCurrent().getPressureMb());
            mBinding.pressureValue.setText(String.format("%s mbar", pressureMbr));


            String windKph = String.valueOf(response.getCurrent().getWindKph());
            mBinding.windValue.setText(windKph + " Kph");

            String realFeelsC = String.valueOf(response.getCurrent().getFeelsLikeC());
            mBinding.feelsLikeValue.setText(String.format("%s°C", realFeelsC));
        } else {

            int currentTempF = (int) response.getCurrent().getTempF();
            mBinding.currentTemp.setText(String.format("%s°F", currentTempF));

            String pressureMbr = String.valueOf(response.getCurrent().getPressureMb());
            mBinding.pressureValue.setText(String.format("%s in", pressureMbr));

            String windMph = String.valueOf(response.getCurrent().getWindMph());
            mBinding.windValue.setText(windMph + " mph");

            String realFeelsF = String.valueOf(response.getCurrent().getFeelsLikeF());
            mBinding.feelsLikeValue.setText(String.format("%s°F", realFeelsF));
        }
    }

    private void showConditionIcon(Long code, boolean isDay) {
        Log.d(TAG, "showConditionIcon: code = " + code + " is day = " + isDay);
        int animationResId;
        if (isDay) {
            animationResId = WeatherIconMapper.getDayAnimation(code);
        } else {
            animationResId = WeatherIconMapper.getNightAnimation(code);
        }

        mBinding.conditionImage.setAnimation(animationResId);
    }

    // fetch today weather
    private void getTodayWeatherData() {
        Log.d(TAG, "getTodayWeatherData: ");
        mViewModel.getTodayWeatherResponse(SessionManager.getString(Constant.CITY_KEY));

    }
}