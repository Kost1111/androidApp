package com.example.myapplication_finalnavigatio.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_finalnavigatio.databinding.FragmentHomeBinding
import com.example.myapplication_finalnavigatio.ui.animalAdapter.Animal
import com.example.myapplication_finalnavigatio.ui.animalAdapter.AnimalAdapter

const val keyName = "addName"
const val imgURLKey = "imgURL"
const val descriptionKey = "description"
const val booleanKey = "bool"
val likeAnimals = mutableListOf<Animal>()

class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: HomeFragmentViewModel

    @SuppressLint("MissingInflatedId", "FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm = HomeFragmentViewModel()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        vm.addUserAnimal(arguments)
        val adapter = AnimalAdapter()
        adapter.itemClick = vm.itemClick(view = root)
        adapter.likeClick = vm.likeClick
        vm.animalsLive.observe(this) {
            it.let {
                adapter.submitList(it)
            }
        }
        binding.cardView.setOnClickListener {
            vm.goToPreviewFragment(view = root)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}