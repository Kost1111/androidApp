package com.example.myapplication_finalnavigatio.ui.details

import android.graphics.BlurMaskFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentDetailsBinding
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment
import com.example.myapplication_finalnavigatio.utils.imgURLKey


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentDetailsBinding =
        FragmentDetailsBinding::inflate
    private lateinit var vm: DetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgURL = arguments?.getString(imgURLKey)
        imgURL?.let { setImg(it) }
        addBlur()
        vm = DetailsViewModel()
        vm.loadingStateLiveData.observeForever {
            binding.progressBar.isVisible = it
        }
        vm.loadingFact.observeForever {
            binding.longDescription.text = it
        }
        vm.loadFacts()
    }

    private fun addBlur() {
        binding.longDescription.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val radius: Float = binding.longDescription.textSize / 3
        val filter = BlurMaskFilter(radius, BlurMaskFilter.Blur.SOLID)
        binding.longDescription.paint.maskFilter = filter
    }

    private fun setImg(imgURL: String) {
        Glide.with(binding.imageViewAnimals)
            .load(imgURL)
            .centerCrop()
            .error(R.drawable.error)
            .into(binding.imageViewAnimals)
    }
}
