package com.example.myapplication_finalnavigatio.ui.details

import android.graphics.BlurMaskFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.catFactApi.FactApi
import com.example.myapplication_finalnavigatio.databinding.FragmentDetailsBinding
import com.example.myapplication_finalnavigatio.ui.home.imgURLKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        val imgURL = arguments?.getString(imgURLKey)
        Glide.with(binding.imageViewAnimals)
            .load(imgURL)
            .centerCrop()
            .error(R.drawable.error)
            .into(binding.imageViewAnimals)

        val retrofit = Retrofit.Builder().baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding.longDescription.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val radius: Float = binding.longDescription.textSize / 3
        val filter = BlurMaskFilter(radius, BlurMaskFilter.Blur.SOLID)
        binding.longDescription.paint.maskFilter = filter

        val factApi = retrofit.create(FactApi::class.java)
        lifecycleScope.launch(Dispatchers.Main) {
            val fact = withContext(Dispatchers.IO) { factApi.getFactApi().fact }
            binding.longDescription.text = fact
        }
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
