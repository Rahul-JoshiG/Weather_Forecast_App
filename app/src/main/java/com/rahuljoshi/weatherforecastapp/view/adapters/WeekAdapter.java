package com.rahuljoshi.weatherforecastapp.view.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahuljoshi.weatherforecastapp.databinding.WeekWeatherLayoutBinding;
import com.rahuljoshi.weatherforecastapp.model.forecastdatamodel.ForecastDay;
import com.rahuljoshi.weatherforecastapp.model.forecastdatamodel.WeatherResponse;

import java.util.List;

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
        // Make sure this reflects the actual number of days forecasted.
        // For a single week forecast, it should be 7.
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
            int forecastIndex = 0;

            String date = forecastDay.getDate();
            binding.dayDate.setText(date);

            String dayTemp = String.format("%.1f °C", forecastDay.getHour().get(forecastIndex).getTemp_c());
            binding.dayTemp.setText(dayTemp);

            String windSpeed = String.format("%.1f km/h", forecastDay.getHour().get(forecastIndex).getWind_kph());
            binding.dayWindSpeed.setText(windSpeed);

            String iconUrl = "https:" + weatherResponse.getCurrent().getCondition().getIcon();
            Glide.with(binding.getRoot()).load(iconUrl).into(binding.dayWeatherCondition);

            forecastIndex++;
        }
    }
}