package com.example.myapplication_finalnavigatio.ui.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myapplication_finalnavigatio.databinding.FragmentDetailsBinding


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
        vm.catFact(binding)

        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
