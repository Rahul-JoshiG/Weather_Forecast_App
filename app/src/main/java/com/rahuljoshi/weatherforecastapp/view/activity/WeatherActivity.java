package com.rahuljoshi.weatherforecastapp.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rahuljoshi.weatherforecastapp.R;
import com.rahuljoshi.weatherforecastapp.databinding.ActivityWeatherBinding;
import com.rahuljoshi.weatherforecastapp.utils.Constant;
import com.rahuljoshi.weatherforecastapp.utils.LocationTracker;
import com.rahuljoshi.weatherforecastapp.utils.SessionManager;
import com.rahuljoshi.weatherforecastapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";
    private ActivityWeatherBinding mBinding;

    private WeatherViewModel mViewModel;
    private final List<String> permissionsRejected = new ArrayList<>();
    private final List<String> permissions = new ArrayList<>();
    private LocationTracker locationTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Log.d(TAG, "onCreate: Weather activity created");
        mViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        mBinding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        applyWindowInsets();
        setUpNavController();

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        List<String> permissionsToRequest = findUnAskedPermissions(permissions);

        // Request permissions if needed, otherwise access the location
        if (!permissionsToRequest.isEmpty()) {
            requestPermissions(permissionsToRequest.toArray(new String[0]), 101);
        } else {
            accessLocation();
        }
    }

    private void setUpNavController() {
        Log.d(TAG, "setupNavController: ");
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_view);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        // Set up the bottom navigation view with the NavController
        NavigationUI.setupWithNavController(mBinding.bottomView, navController);

        // This ensures selecting an item in the bottom navigation automatically navigates
        mBinding.bottomView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            // Determine the navigation graph ID based on the selected item
            int navGraphId;
            int itemId = item.getItemId();
            if (itemId == R.id.today) {
                navGraphId = R.id.todayFragment;
            } else if (itemId == R.id.week) {
                navGraphId = R.id.weekFragment;
            } else if (itemId == R.id.setting) {
                navGraphId = R.id.settingFragment;
            } else {
                return false;
            }

            // Navigate using the NavController
            navController.navigate(navGraphId);
            return true; // Indicate that the item selection was handled
        });
    }

    private void applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private List<String> findUnAskedPermissions(List<String> wanted) {
        List<String> result = new ArrayList<>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }
        return result;
    }

    private boolean hasPermission(String permission) {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void accessLocation() {
        locationTrack = new LocationTracker(WeatherActivity.this);

        if (locationTrack.canGetLocation()) {
            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();
            Log.d(TAG, "accessLocation: Longitude: " + longitude + "\nLatitude: " + latitude);
            fetchLocationNameUsingLatAndLong(latitude, longitude);
        } else {
            locationTrack.showSettingsAlert();
        }
    }

    private void fetchLocationNameUsingLatAndLong(double latitude, double longitude) {
        Log.d(TAG, "fetchLocationNameUsingLatAndLong: ");
        Log.d(TAG, "fetchLastLocation: Location fetched - Lat: " + latitude + ", Long: " + longitude);
        String latAndLong = latitude + "," + longitude;
        mViewModel.fetchLocation(latAndLong);

        // Observe the weather data LiveData
        mViewModel.getWeatherLocation().observe(this, response -> {
            if (response != null && response.getLocation() != null) {
                Log.d(TAG, "Weather data fetched: " + response.getLocation().getName());
                String cityName = response.getLocation().getName();
                SessionManager.putString(Constant.CITY_KEY, cityName);
                mViewModel.updateLocation(cityName);
            } else {
                Log.e(TAG, "Unable to fetch weather data");
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    String permission = permissions[i];
                    if (!hasPermission(permission)) {
                        permissionsRejected.add(permission);
                    }
                }
            }

            if (!permissionsRejected.isEmpty()) {
                if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                    showMessageOKCancel((dialog, which) -> requestPermissions(permissionsRejected.toArray(new String[0]), 101));
                }
            } else {
                // Permissions granted, access the location
                accessLocation();
            }
        }
    }

    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(WeatherActivity.this)
                .setMessage("These permissions are mandatory for the application. Please allow access.")
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Close the application if "Cancel" is clicked
                    finishAffinity(); // Closes all activities and exits the app
                })
                .create()
                .show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationTrack != null) {
            locationTrack.stopListener();
        }
    }

}