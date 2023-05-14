package com.example.myapplication_finalnavigatio.ui.add_animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentAddAnimalBinding
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment
import com.example.myapplication_finalnavigatio.ui.home.descriptionKey
import com.example.myapplication_finalnavigatio.ui.home.imgURLKey
import com.example.myapplication_finalnavigatio.ui.home.keyName


class AddAnimalFragment : BaseFragment<FragmentAddAnimalBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentAddAnimalBinding =
        FragmentAddAnimalBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnAddAnimal.setOnClickListener {
            addAnimal(binding.root)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun addAnimal(root: View) {
        val bundle = Bundle()
        val imgURL = binding.tvImgURL.text.toString()
        val name = binding.tvNameAddAnimal.text.toString()
        val description = binding.tvDescription.text.toString()
        bundle.putString(keyName, name)
        bundle.putString(imgURLKey, imgURL)
        bundle.putString(descriptionKey, description)
        Navigation.findNavController(root)
            .navigate(R.id.action_addAnimal_to_animalPreview, bundle)
    }
}