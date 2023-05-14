package com.example.myapplication_finalnavigatio.ui.Settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentSettingsBinding
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment
import java.util.*


@SuppressLint("CommitPrefEdits")
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentSettingsBinding =
        FragmentSettingsBinding::inflate
    private lateinit var vm: SettingViewModel
    private var fullScreenSaveState = "fullScreen"
    private var nightThemeSaveState = "theme"
    private var langSaveState = "lang"
    private lateinit var sharedPreferencesFullScreen: SharedPreferences
    private lateinit var sharedPreferencesTheme: SharedPreferences
    private lateinit var sharedPreferencesLang: SharedPreferences

    override fun onAttach(context: Context) {
        sharedPreferencesFullScreen =
            context.getSharedPreferences(fullScreenSaveState, Context.MODE_PRIVATE)
        sharedPreferencesTheme =
            context.getSharedPreferences(nightThemeSaveState, Context.MODE_PRIVATE)
        sharedPreferencesLang =
            context.getSharedPreferences(langSaveState, Context.MODE_PRIVATE)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = SettingViewModel()
        binding.switchFullScreen.isChecked =
            sharedPreferencesFullScreen.getBoolean(fullScreenSaveState, false)
        binding.switchTheme.isChecked =
            sharedPreferencesTheme.getBoolean(nightThemeSaveState, false)

        binding.switchTheme.setOnCheckedChangeListener { _, _ ->
            colorChange(binding.switchTheme.isChecked)
        }
        binding.switchFullScreen.setOnCheckedChangeListener { _, _ ->
            fullScreenChange(binding.switchFullScreen.isChecked)
        }
        binding.btnEng.setOnClickListener {
            showPopup(binding.btnEng)
        }
    }

    @SuppressWarnings("deprecation")
    private fun change(lang: String) {
        val config = resources.configuration
        val locale = Locale(lang)
        Locale.setDefault(locale)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        activity?.recreate()
    }

    private fun showPopup(button: Button) {
        val popup = PopupMenu(requireContext(), button)
        popup.inflate(R.menu.header_menu)
        popup.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.header1 -> {
                    change("ru")
                    sharedPreferencesLang.edit().putString("ru", "en").apply()
                }
                R.id.header2 -> {
                    change("en")
                    sharedPreferencesLang.edit().putString("en", "en").apply()
                }
            }
            true
        }
        popup.show()
    }

    private fun hideSystemUI() {
        requireActivity().window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun showSystemUI() {
        requireActivity().window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION and View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    }

    private fun colorChange(boolean: Boolean) {
        if (boolean) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPreferencesTheme.edit().putBoolean(nightThemeSaveState, true).apply()

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPreferencesTheme.edit().putBoolean(nightThemeSaveState, false).apply()
        }
    }

    private fun fullScreenChange(boolean: Boolean) {
        if (boolean) {
            hideSystemUI()
            sharedPreferencesFullScreen.edit().putBoolean(fullScreenSaveState, true).apply()
        } else {
            showSystemUI()
            sharedPreferencesFullScreen.edit().putBoolean(fullScreenSaveState, false).apply()
        }
    }
}


