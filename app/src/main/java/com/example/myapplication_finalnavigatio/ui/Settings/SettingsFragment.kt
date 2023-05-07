package com.example.myapplication_finalnavigatio.ui.Settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
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
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.header1 -> {
                    change("ru")
                    Toast.makeText(requireContext(), "set russian lang", Toast.LENGTH_SHORT).show()
                }
                R.id.header2 -> {
                    change("en")
                    Toast.makeText(requireContext(), "set english lang", Toast.LENGTH_SHORT).show()
                }
            }
            true
        })
        popup.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}