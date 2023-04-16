package com.example.myapplication_finalnavigatio.ui.animalPreview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentAnimalPreviewBinding
import com.example.myapplication_finalnavigatio.ui.home.booleanKey
import com.example.myapplication_finalnavigatio.ui.home.descriptionKey
import com.example.myapplication_finalnavigatio.ui.home.imgURLKey
import com.example.myapplication_finalnavigatio.ui.home.keyName


class AnimalPreviewFragment : Fragment() {
    private var _binding: FragmentAnimalPreviewBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAnimalPreviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val name = arguments?.getString(keyName)
        val imgURL = arguments?.getString(imgURLKey)
        val description = arguments?.getString(descriptionKey)

        binding.tvNamePreview.text = name
        binding.tvDescriptionPreview.text = description
        Glide.with(binding.ivPreviewAvatar)
            .load(imgURL)
            .centerCrop()
            .error(R.drawable.error)
            .into(binding.ivPreviewAvatar)

        binding.butPreview.setOnClickListener {
            if (name == "" || imgURL == "" || description == "") {
                Toast.makeText(context, "Enter patam your animal!", Toast.LENGTH_LONG).show()
                Navigation.findNavController(root)
                    .navigate(R.id.action_animalPreview_to_addAnimal)
            } else {
                val bundle2 = Bundle()
                val temp = true
                bundle2.putBoolean(booleanKey, temp)
                bundle2.putString(keyName, name)
                bundle2.putString(imgURLKey, imgURL)
                bundle2.putString(descriptionKey, description)

                Toast.makeText(
                    context,
                    "Your animal has been successfully added !",
                    Toast.LENGTH_LONG
                ).show()
                Navigation.findNavController(root)
                    .navigate(R.id.action_animalPreview_to_navigation_home, bundle2)
            }
        }
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}