package com.example.myapplication_finalnavigatio.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication_finalnavigatio.Dependencies
import com.example.myapplication_finalnavigatio.databinding.FragmentFavouriteBinding
import com.example.myapplication_finalnavigatio.ui.animal_adapter.LikedAnimalAdapter
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentFavouriteBinding =
        FragmentFavouriteBinding::inflate
    private val vm by lazy { FavouriteViewModel(Dependencies.statisticRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Dependencies.init(requireContext())
        vm.likedAnimals.observe(viewLifecycleOwner) { allLikedAnimals ->
            if (allLikedAnimals.isEmpty()) {
                binding.constraintPlaseHolder.visibility = View.VISIBLE
                binding.recyclerViewLike.visibility = View.INVISIBLE
            } else {
                val adapter = LikedAnimalAdapter(vm.likedAnimalsItemListener)
                adapter.data = allLikedAnimals
                binding.recyclerViewLike.adapter = adapter
                binding.recyclerViewLike.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}