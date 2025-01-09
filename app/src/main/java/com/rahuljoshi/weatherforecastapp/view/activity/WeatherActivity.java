package com.rahuljoshi.weatherforecastapp.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.rahuljoshi.weatherforecastapp.R;
import com.rahuljoshi.weatherforecastapp.databinding.ActivityWeatherBinding;
import com.rahuljoshi.weatherforecastapp.utils.Constant;
import com.rahuljoshi.weatherforecastapp.utils.SessionManager;
import com.rahuljoshi.weatherforecastapp.view.fragments.SettingFragment;
import com.rahuljoshi.weatherforecastapp.view.fragments.TodayFragment;
import com.rahuljoshi.weatherforecastapp.view.fragments.WeekFragment;
import com.rahuljoshi.weatherforecastapp.viewmodel.WeatherViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";
    private WeatherViewModel mViewModel;

    private static final int PERMISSION_ID = 44;

    private ActivityWeatherBinding mBinding;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        // Enable edge-to-edge UI
        EdgeToEdge.enable(this);
        Log.d(TAG, "onCreate: Weather activity created");

        // Bind the layout
        mBinding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // Check dark mode settings and apply
        checkingDarkMode();

        // Initialize location services
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Apply edge-to-edge layout adjustments
        applyWindowInsets();

        // Set up bottom navigation listeners
        setOnClickListener();

        // Fetch location if permissions are already granted
        if (checkPermissions()) {
            fetchLastLocation();
        } else {
            requestPermissions();
        }
    }

    private void applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Check dark mode settings and apply
    private void checkingDarkMode() {
        Log.d(TAG, "checkingDarkMode: Checking dark mode settings");
        boolean isDark = SessionManager.getBoolean(Constant.DARK_MODE);
        AppCompatDelegate.setDefaultNightMode(
                isDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }

    // Set up bottom navigation listeners
    private void setOnClickListener() {
        Log.d(TAG, "setOnClickListener: Setting up bottom navigation listeners");
        mBinding.bottomView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.today) {
                openFragment(new TodayFragment());
            } else if (itemId == R.id.seven_day) {
                openFragment(new WeekFragment());
            } else if (itemId == R.id.setting) {
                openFragment(new SettingFragment());
            }
            return false;
        });
    }

    // Open a fragment
    private void openFragment(Fragment fragment) {
        Log.d(TAG, "openFragment: Opening " + fragment.getClass().getSimpleName());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_view, fragment);
        transaction.commit();
    }

    // Check for location permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    // Request location permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        }, PERMISSION_ID);
    }

    // Handle permission request result
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onRequestPermissionsResult: Permission granted, fetching location");
            fetchLastLocation();
        } else {
            Log.d(TAG, "onRequestPermissionsResult: Permission denied");
        }
    }

    // Fetch the last known location
    @SuppressLint("MissingPermission")
    private void fetchLastLocation() {
        Task<Location> locationTask = mFusedLocationClient.getLastLocation();
        locationTask.addOnSuccessListener(location -> {
            if (location != null) {
                Log.d(TAG, "fetchLastLocation: Location fetched - Lat: " + location.getLatitude() + ", Long: " + location.getLongitude());
                String latAndLong = location.getLatitude() + "," + location.getLongitude();
                mViewModel.fetchLocation(latAndLong);

                // Observe the weather data LiveData
                mViewModel.getWeatherLocation().observe(this, response -> {
                    if (response != null && response.getLocation() != null) {
                        Log.d(TAG, "Weather data fetched: " + response.getLocation().getName());
                        String cityName = response.getLocation().getName();
                        SessionManager.putString(Constant.CITY_KEY, cityName);
                        // Open TodayFragment with city name
                        openFragment(TodayFragment.newInstance(cityName));
                    } else {
                        Log.e(TAG, "Unable to fetch weather data");
                    }
                });

            } else {
                Log.w(TAG, "fetchLastLocation: No location available");
            }
        }).addOnFailureListener(e -> Log.e(TAG, "fetchLastLocation: Failed to fetch location", e));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewModel.getWeatherLocation().hasObservers()) {
            mViewModel.getWeatherLocation().removeObservers(this);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            fetchLastLocation();
        } else {
            requestPermissions();
        }
    }
}