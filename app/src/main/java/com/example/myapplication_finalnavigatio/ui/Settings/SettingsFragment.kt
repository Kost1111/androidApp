package com.example.myapplication_finalnavigatio.ui.Settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentSettingsBinding
import java.util.*


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: SettingViewModel

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm = SettingViewModel()
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.switchSettings.setOnCheckedChangeListener { _, _ ->
            vm.colorChange(binding)
        }
        binding.switchFullScreen.setOnCheckedChangeListener { _, _ ->
            if (binding.switchFullScreen.isChecked) {
                hideSystemUI()
            } else {
                showSystemUI()
            }
        }
        binding.btnEng.setOnClickListener {
            showPopup(binding.btnEng)
        }

        return root
    }


    @SuppressWarnings("deprecation")
    fun change(lang: String) {
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
                }
                R.id.header2 -> {
                    change("en")
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}