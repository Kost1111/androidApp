package com.example.myapplication_finalnavigatio.ui.details

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.cat_fact_api.model.CatFacts
import com.example.myapplication_finalnavigatio.cat_fact_api.FactApi
import com.example.myapplication_finalnavigatio.databinding.FragmentDetailsBinding
import com.example.myapplication_finalnavigatio.ui.home.imgURLKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun catFact(binding: FragmentDetailsBinding) {
        val api = FactApi.create().getFact()
        api.enqueue(object : Callback<CatFacts> {
            override fun onResponse(call: Call<CatFacts>, response: Response<CatFacts>) {
                binding.progressBar.visibility = View.GONE
                binding.longDescription.text = response.body()?.fact
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<CatFacts>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                binding.longDescription.setTextColor(Color.RED)
                binding.longDescription.text = "${t.message}"
            }
        })
    }
}