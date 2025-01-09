package com.rahuljoshi.weatherforecastapp.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.rahuljoshi.weatherforecastapp.databinding.FragmentSettingBinding;
import com.rahuljoshi.weatherforecastapp.utils.Constant;
import com.rahuljoshi.weatherforecastapp.utils.SessionManager;

public class SettingFragment extends Fragment {
    private final String TAG = "SettingFragment";
    private FragmentSettingBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentSettingBinding.inflate(inflater, container, false);
        Log.d(TAG, "onCreateView: for setting fragment");
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: for setting fragment");

        // Initialize toggle state based on saved preferences
        boolean isDarkMode = SessionManager.getBoolean(Constant.DARK_MODE);
        mBinding.toggleTheme.setChecked(isDarkMode);

        // Set OnCheckedChangeListener for the toggle switch
        mBinding.toggleTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                SessionManager.putBoolean(Constant.DARK_MODE, true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                SessionManager.putBoolean(Constant.DARK_MODE, false);
            }
        });

        mBinding.searchCityView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Save the city name in SessionManager
                SessionManager.putString(Constant.CITY_KEY, query);
                mBinding.searchCityView.clearFocus();
                Toast.makeText(requireContext(), "City Updated", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}