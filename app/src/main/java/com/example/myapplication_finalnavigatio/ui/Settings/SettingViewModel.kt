package com.example.myapplication_finalnavigatio.ui.Settings

import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.example.myapplication_finalnavigatio.databinding.FragmentSettingsBinding

class SettingViewModel : ViewModel() {

    fun colorChange(binding: FragmentSettingsBinding) {
        if (binding.switchTheme.isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}