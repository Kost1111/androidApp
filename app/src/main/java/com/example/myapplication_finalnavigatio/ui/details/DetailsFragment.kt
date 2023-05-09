package com.example.myapplication_finalnavigatio.ui.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication_finalnavigatio.databinding.FragmentDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: DetailsViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm = DetailsViewModel()
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root = binding.root

        vm.setImg(binding, arguments)
        vm.addBlur(binding)
        lifecycleScope.launch(Dispatchers.Main) {
            val fact = withContext(Dispatchers.IO) { vm.factApi.getFactApi().fact }
            if (fact !== null) {
                binding.progressBar.visibility = View.INVISIBLE
                binding.longDescription.text = fact
            }
        }
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
