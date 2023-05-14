package com.example.myapplication_finalnavigatio.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_finalnavigatio.databinding.FragmentHomeBinding
import com.example.myapplication_finalnavigatio.ui.animal_adapter.Animal
import com.example.myapplication_finalnavigatio.ui.animal_adapter.AnimalAdapter
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment

const val keyName = "addName"
const val imgURLKey = "imgURL"
const val descriptionKey = "description"
const val booleanKey = "bool"
val likeAnimals = mutableListOf<Animal>()

class Home : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentHomeBinding =
        FragmentHomeBinding::inflate
    private lateinit var vm: HomeFragmentViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = HomeFragmentViewModel()
        vm.addUserAnimal(arguments)
        val adapter = AnimalAdapter()
        adapter.itemClick = vm.itemClick(view = binding.root)
        adapter.likeClick = vm.likeClick
        vm.animalsLive.observe(this) {
            it.let {
                adapter.submitList(it)
            }
        }
        binding.cardView.setOnClickListener {
            vm.goToPreviewFragment(view = binding.root)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }
}