package com.example.myapplication_finalnavigatio.ui.animal_preview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentAnimalPreviewBinding
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment


class AnimalPreviewFragment : BaseFragment<FragmentAnimalPreviewBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentAnimalPreviewBinding =
        FragmentAnimalPreviewBinding::inflate
    private lateinit var vm: AnimalPreviewViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = AnimalPreviewViewModel()
        vm.loadData(arguments)

        var name: String? = vm.resultName.value
        var imgURL: String? = vm.resultImgURL.value
        var description: String? = vm.resultDescription.value

        vm.resultName.observe(this) {
            name = it
        }
        vm.resultImgURL.observe(this) {
            imgURL = it
        }
        vm.resultDescription.observe(this) {
            description = it
        }

        binding.tvNamePreview.text = name
        binding.tvDescriptionPreview.text = description
        Glide.with(binding.ivPreviewAvatar)
            .load(imgURL)
            .centerCrop()
            .error(R.drawable.error)
            .into(binding.ivPreviewAvatar)

        binding.butPreview.setOnClickListener {
            vm.animalPreview(binding.root, name, description, imgURL)
        }
    }
}