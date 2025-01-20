package com.rahuljoshi.weatherforecastapp.view.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rahuljoshi.weatherforecastapp.databinding.FragmentSettingBinding;
import com.rahuljoshi.weatherforecastapp.utils.Constant;
import com.rahuljoshi.weatherforecastapp.utils.SessionManager;
import com.rahuljoshi.weatherforecastapp.viewmodel.WeatherViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingFragment extends Fragment {
    private final String TAG = "SettingFragment";
    private FragmentSettingBinding mBinding;
    private WeatherViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentSettingBinding.inflate(inflater, container, false);
        Log.d(TAG, "onCreateView: for setting fragment");
        mViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: for setting fragment");
        checkingMode();

        setOnClickListener();

    }

    private void setOnClickListener() {
        Log.d(TAG, "setOnClickListener: ");
        mBinding.ll2.setOnClickListener(v -> showViableUnitsDialogBox());

        mBinding.ll.setOnClickListener(v->{
            showSearchBoxAndUpdateTheCity();
        });

    }

    private void showSearchBoxAndUpdateTheCity() {
        Log.d(TAG, "showSearchBoxAndUpdateTheCity: Initializing search box");

        // Create an AlertDialog for city input
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Search City");
        builder.setMessage("Enter the city name:");

        // Create an EditText for user input
        final EditText input = new EditText(requireContext());
        input.setHint("e.g., New York");
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Update", (dialog, which) -> {
            String cityName = input.getText().toString().trim();

            if (cityName.isEmpty()) {
                Log.w(TAG, "showSearchBoxAndUpdateTheCity: No city entered");
                Toast.makeText(requireContext(), "City name cannot be empty!", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "showSearchBoxAndUpdateTheCity: Updating city to " + cityName);
                //SessionManager.putString(Constant.CITY_KEY, cityName);
                mViewModel.updateLocation(cityName);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            Log.d(TAG, "showSearchBoxAndUpdateTheCity: Search box canceled");
            dialog.cancel();
        });
        // Show the dialog
        builder.show();
    }


    private void showViableUnitsDialogBox() {
        Log.d(TAG, "showViableUnitsDialogBox: Initializing unit selection dialog");

        // Define unit options
        String[] unitOptions = {"Metric (°C)", "Imperial (°F)"};

        // Retrieve current unit preference
        String currentUnit = SessionManager.getUnit(Constant.UNIT_NAME);
        int selectedIndex = currentUnit.equals("imperial") ? 1 : 0; // Default to Metric if not set

        // Create a single-choice dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select Unit System")
                .setSingleChoiceItems(unitOptions, selectedIndex, (dialog, which) -> {
                    // Handle selection
                    String selectedUnit = which == 0 ? "metric" : "imperial";
                    String unitLabel = which == 0 ? "Metric" : "Imperial";

                    // Save the selection and update UI
                    SessionManager.putString(Constant.UNIT_NAME, selectedUnit);
                    mBinding.unitSystemValue.setText(unitLabel);

                    Log.d(TAG, "Unit system updated to: " + unitLabel);

                    // Dismiss dialog after selection
                    dialog.dismiss();
                });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void checkingMode() {
        Log.d(TAG, "checkingMode: ");

        // Check current system theme
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        // Determine if system is in dark mode
        boolean isSystemDarkMode = (nightModeFlags == Configuration.UI_MODE_NIGHT_YES);

        // Check if the user has previously selected a theme
        boolean isDarkMode = SessionManager.getBoolean(Constant.DARK_MODE);

        // If no user preference exists, use the system default
        if (!SessionManager.getBoolean(Constant.DARK_MODE)) {
            isDarkMode = isSystemDarkMode;
            SessionManager.putBoolean(Constant.DARK_MODE, isDarkMode);
        }

        // Update the switch state
        mBinding.toggleTheme.setChecked(isDarkMode);

        // Listen for toggle changes
        mBinding.toggleTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                SessionManager.putBoolean(Constant.DARK_MODE, true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                SessionManager.putBoolean(Constant.DARK_MODE, false);
            }
        });
    }

}