package com.example.myapplication_finalnavigatio.ui.Settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.example.myapplication_finalnavigatio.databinding.FragmentSettingsBinding

class SettingViewModel : ViewModel() {

    fun colorChange(binding: FragmentSettingsBinding) {
        if (binding.switchSettings.isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}