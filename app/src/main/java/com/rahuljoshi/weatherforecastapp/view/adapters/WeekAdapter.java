package com.rahuljoshi.weatherforecastapp.view.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahuljoshi.weatherforecastapp.databinding.WeekWeatherLayoutBinding;
import com.rahuljoshi.weatherforecastapp.model.data.forecastmodel.ForecastDay;
import com.rahuljoshi.weatherforecastapp.model.data.forecastmodel.Hour;
import com.rahuljoshi.weatherforecastapp.model.data.forecastmodel.WeatherResponse;
import com.rahuljoshi.weatherforecastapp.utils.Constant;
import com.rahuljoshi.weatherforecastapp.utils.SessionManager;
import com.rahuljoshi.weatherforecastapp.utils.WeatherIconMapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder> {
    private final String TAG = "WeekAdapter";
    private final List<ForecastDay> mDay;
    private WeatherResponse mWeatherResponse;

    public WeekAdapter(List<ForecastDay> mWeatherResponseList, WeatherResponse weatherResponse) {
        this.mDay = mWeatherResponseList;
        this.mWeatherResponse = weatherResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeekWeatherLayoutBinding binding = WeekWeatherLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ForecastDay day = mDay.get(position);
        holder.bind(day, mWeatherResponse);
    }

    @Override
    public int getItemCount() {
        return mDay.size();
    }

    public void updateData(List<ForecastDay> newForecasts, WeatherResponse weatherResponse) {
        mDay.clear();
        mDay.addAll(newForecasts);
        this.mWeatherResponse = weatherResponse;
        notifyDataSetChanged(); // Notify the adapter that data has changed
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        WeekWeatherLayoutBinding binding;

        public ViewHolder(WeekWeatherLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ForecastDay forecastDay, WeatherResponse weatherResponse) {
            Log.d("ViewHolder", "bind: ");

            long currentDateEpoch = forecastDay.getDate_epoch();
            Date date = new Date(currentDateEpoch * 1000);
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd", Locale.ENGLISH);
            String currentDate = formatter.format(date);
            binding.dayDateText.setText(currentDate);

            String conditionName = weatherResponse.getCurrent().getCondition().getText();
            binding.dayConditionName.setText(conditionName);

            Long code = (long) weatherResponse.getCurrent().getCondition().getCode();
            boolean isDay = weatherResponse.getCurrent().getIs_day() == 1;

            setUpConditionAnimation(code, isDay);

            if (SessionManager.getUnit(Constant.UNIT_NAME).equals(Constant.UNIT_NAME)) {
                updateUiForMetric(forecastDay);
            } else {
                updateUiForImperial(forecastDay);
            }
        }

        private void setUpConditionAnimation(Long code, boolean isDay) {
            Log.d("WeekAdapter", "setUpConditionAnimation: ");
            int animationResId;

            if(isDay){
                animationResId = WeatherIconMapper.getDayAnimation(code);
            }else{
                animationResId = WeatherIconMapper.getNightAnimation(code);
            }

            binding.dayConditionIcon.setAnimation(animationResId);

        }

        private void updateUiForMetric(ForecastDay forecastDay) {
            Log.d("WeekAdapter", "updateUiForMetric:");

            // Get the current hour in 24-hour format
            int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

            // Iterate through the hourly forecast data
            for (Hour hourData : forecastDay.getHour()) { // Assuming Hour is a model class for hourly data
                // Extract the hour part from the time string (e.g., "2025-01-20 10:00")
                String time = hourData.getTime()    ;
                int hourOfDay = Integer.parseInt(time.split(" ")[1].split(":")[0]);

                // Check if the hour matches the current hour
                if (hourOfDay == currentHour) {
                    int dayTempC = (int) hourData.getTemp_c();
                    binding.dayTemp.setText(String.format("%s°C", dayTempC));
                    Log.d("WeekAdapter", "updateUiForMetric: Found matching hour, updated temperature.");
                    return;
                }
            }

            // Fallback if no matching hour is found
            Log.d("WeekAdapter", "updateUiForMetric: No matching hour data found");
        }



        private void updateUiForImperial(ForecastDay forecastDay) {
            Log.d("WeekAdapter", "updateUiForImperial: ");

            // Get the current hour in 24-hour format
            int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

            // Iterate through the hourly forecast data
            for (Hour hourData : forecastDay.getHour()) { // Assuming Hour is a model class for hourly data
                // Extract the hour part from the time string (e.g., "2025-01-20 10:00")
                String time = hourData.getTime()    ;
                int hourOfDay = Integer.parseInt(time.split(" ")[1].split(":")[0]);

                // Check if the hour matches the current hour
                if (hourOfDay == currentHour) {
                    int dayTempF = (int) hourData.getTemp_f();
                    binding.dayTemp.setText(String.format("%s°F", dayTempF));
                    Log.d("WeekAdapter", "updateUiForMetric: Found matching hour, updated temperature.");
                    return;
                }
            }

            // Fallback if no matching hour is found
            Log.d("WeekAdapter", "updateUiForMetric: No matching hour data found");
        }
    }
}