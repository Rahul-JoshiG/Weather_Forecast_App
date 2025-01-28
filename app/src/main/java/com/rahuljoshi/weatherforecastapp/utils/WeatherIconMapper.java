package com.rahuljoshi.weatherforecastapp.utils;

import com.rahuljoshi.weatherforecastapp.R;

import java.util.HashMap;
import java.util.Map;

public class WeatherIconMapper {

    private static final Map<Long, Integer> dayAnimationMap = new HashMap<>();
    private static final Map<Long, Integer> nightAnimationMap = new HashMap<>();

    static {
        // Day animations
        dayAnimationMap.put(1000L, R.raw.day_clear);
        dayAnimationMap.put(1006L, R.raw.day_cloudy);
        dayAnimationMap.put(1195L, R.raw.day_rain);
        dayAnimationMap.put(1225L, R.raw.day_snow);
        dayAnimationMap.put(1273L, R.raw.day_thunder);
        dayAnimationMap.put(1030L, R.raw.day_mist);
        dayAnimationMap.put(1003L, R.raw.partly_cloudly);
        dayAnimationMap.put(1009L, R.raw.day_overcast);


        nightAnimationMap.put(1000L, R.raw.night_clear);
        nightAnimationMap.put(1006L, R.raw.night_cloudy);
        nightAnimationMap.put(1195L, R.raw.night_rain);
        nightAnimationMap.put(1225L, R.raw.night_snow);
        nightAnimationMap.put(1273L, R.raw.night_thunder);
        nightAnimationMap.put(1030L, R.raw.night_mist);
        nightAnimationMap.put(1003L, R.raw.partly_cloudly);
        nightAnimationMap.put(1009L, R.raw.night_overcast);
    }

    /**
     * Get the animation resource for daytime based on the weather condition code.
     *
     * @param conditionCode The weather condition code.
     * @return The animation resource ID for daytime or a default animation if not found.
     */
    public static int getDayAnimation(Long conditionCode) {
        return dayAnimationMap.getOrDefault(conditionCode, R.raw.day_clear);
    }

    /**
     * Get the animation resource for nighttime based on the weather condition code.
     *
     * @param conditionCode The weather condition code.
     * @return The animation resource ID for nighttime or a default animation if not found.
     */
    public static int getNightAnimation(Long conditionCode) {
        return nightAnimationMap.getOrDefault(conditionCode, R.raw.night_clear);
    }

}
