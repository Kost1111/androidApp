package com.example.myapplication_finalnavigatio.ui.details

import android.graphics.BlurMaskFilter
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.catFactApi.FactApi
import com.example.myapplication_finalnavigatio.databinding.FragmentDetailsBinding
import com.example.myapplication_finalnavigatio.ui.home.imgURLKey
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsViewModel : ViewModel() {

    fun addBlur(binding: FragmentDetailsBinding) {
        binding.longDescription.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val radius: Float = binding.longDescription.textSize / 3
        val filter = BlurMaskFilter(radius, BlurMaskFilter.Blur.SOLID)
        binding.longDescription.paint.maskFilter = filter
    }

    fun setImg(binding: FragmentDetailsBinding, argument: Bundle?) {
        val imgURL = argument?.getString(imgURLKey)
        Glide.with(binding.imageViewAnimals)
            .load(imgURL)
            .centerCrop()
            .error(R.drawable.error)
            .into(binding.imageViewAnimals)
    }

    private val retrofit = Retrofit.Builder().baseUrl("https://catfact.ninja/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val factApi = retrofit.create(FactApi::class.java)


}