package com.example.myapplication_finalnavigatio.ui.animal_preview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.Dependencies
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentAnimalPreviewBinding
import com.example.myapplication_finalnavigatio.ui.animal_adapter.Animal
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment
import com.example.myapplication_finalnavigatio.ui.home.animals
import com.example.myapplication_finalnavigatio.utils.descriptionKey
import com.example.myapplication_finalnavigatio.utils.imgURLKey
import com.example.myapplication_finalnavigatio.utils.keyName


class AnimalPreviewFragment : BaseFragment<FragmentAnimalPreviewBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentAnimalPreviewBinding =
        FragmentAnimalPreviewBinding::inflate

    private val vm by lazy { AnimalPreviewViewModel(Dependencies.statisticRepository) }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Dependencies.init(requireContext())
        val name: String? = arguments?.getString(keyName)
        val imgURL: String? = arguments?.getString(imgURLKey)
        val description: String? = arguments?.getString(descriptionKey)

        binding.apply {
            tvNamePreview.text = name
            tvDescriptionPreview.text = description
            Glide.with(ivPreviewAvatar)
                .load(imgURL)
                .centerCrop()
                .error(R.drawable.error)
                .into(ivPreviewAvatar)
            butPreview.setOnClickListener {
                animalPreview(root, name, description, imgURL)
            }
        }
    }

    private fun animalPreview(root: View, name: String?, description: String?, imgURL: String?) {
        if (name == "" || imgURL == "" || description == "") {
            Navigation.findNavController(root)
                .navigate(R.id.action_animalPreview_to_addAnimal)
        } else {
            animals.add(Animal(imgURL, name, description))
            vm.insertNewLikedAnimals(animal = Animal(imgURL, name, description))
            Navigation.findNavController(root)
                .navigate(R.id.action_animalPreview_to_navigation_home)
        }
    }
}