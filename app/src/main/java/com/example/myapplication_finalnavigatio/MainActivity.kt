package com.example.myapplication_finalnavigatio

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication_finalnavigatio.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferencesFullScreen: SharedPreferences
    private lateinit var sharedPreferencesTheme: SharedPreferences

    var fullScreenSaveState = "fullScreen"
    var nightThemeSaveState = "theme"

    @SuppressLint("AppCompatMethod")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferencesFullScreen =
            getSharedPreferences(fullScreenSaveState, Context.MODE_PRIVATE)
        sharedPreferencesTheme =
            getSharedPreferences(nightThemeSaveState, Context.MODE_PRIVATE)

        checkFullScreen(sharedPreferencesTheme.getBoolean(nightThemeSaveState, false))
        checkTheme(sharedPreferencesFullScreen.getBoolean(fullScreenSaveState, false))
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        setupActionBarWithNavController(navController)
        navView.setupWithNavController(navController)
    }

    private fun checkFullScreen(boolean: Boolean) {
        if (boolean) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun checkTheme(boolean: Boolean) {
        if (boolean) {
            hideSystemUI()
            supportActionBar?.show()
        } else {
            showSystemUI()
            supportActionBar?.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun hideSystemUI() {
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun showSystemUI() {
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION and View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    }

}
