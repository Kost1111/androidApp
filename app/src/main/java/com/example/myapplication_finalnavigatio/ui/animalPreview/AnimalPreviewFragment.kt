package com.example.myapplication_finalnavigatio.ui.animalPreview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentAnimalPreviewBinding


class AnimalPreviewFragment : Fragment() {
    private var _binding: FragmentAnimalPreviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: AnimalPreviewViewModel

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation", "FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm = AnimalPreviewViewModel()
        vm.loadData(arguments)

        var name: String? = vm.resultName.value
        var imgURL: String? = vm.resultImgURL.value
        var description: String? = vm.resultDescription.value

        _binding = FragmentAnimalPreviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

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
            vm.animalPreview(root, name, description, imgURL)
        }
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}