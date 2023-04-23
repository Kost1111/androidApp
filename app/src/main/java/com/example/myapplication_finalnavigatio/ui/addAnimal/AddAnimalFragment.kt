package com.example.myapplication_finalnavigatio.ui.addAnimal

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentAddAnimalBinding
import com.example.myapplication_finalnavigatio.ui.home.descriptionKey
import com.example.myapplication_finalnavigatio.ui.home.imgURLKey
import com.example.myapplication_finalnavigatio.ui.home.keyName


class AddAnimalFragment : Fragment() {

    private var _binding: FragmentAddAnimalBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAnimalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnAddAnimal.setOnClickListener {
            addAnimal(root)
        }

        return root
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}