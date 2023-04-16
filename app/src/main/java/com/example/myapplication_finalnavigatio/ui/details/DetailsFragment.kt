package com.example.myapplication_finalnavigatio.ui.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root = binding.root
        val imgURL = arguments?.getString("imgURL")
        Glide.with(binding.imageViewAnimals)
            .load(imgURL)
            .centerCrop()
            .error(R.drawable.error)
            .into(binding.imageViewAnimals)
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
