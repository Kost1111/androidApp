package com.example.myapplication_finalnavigatio.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_finalnavigatio.Dependencies
import com.example.myapplication_finalnavigatio.databinding.FragmentHomeBinding
import com.example.myapplication_finalnavigatio.ui.animal_adapter.AnimalAdapter
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment

class Home : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentHomeBinding =
        FragmentHomeBinding::inflate
    private val vm by lazy { HomeFragmentViewModel(Dependencies.statisticRepository) }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Dependencies.init(requireContext())
        val adapter = AnimalAdapter().apply {
            itemClick = vm.itemClick(view = binding.root)
            likeClick = vm.likeClick
        }
        vm.animalsLive.observe(this) {
            it.let {
                adapter.submitList(it)
            }
        }
        binding.apply {
            cardView.setOnClickListener {
                vm.goToPreviewFragment(view = binding.root)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }
    }
}