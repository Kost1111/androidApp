package com.example.myapplication_finalnavigatio.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_finalnavigatio.databinding.FragmentFavouriteBinding
import com.example.myapplication_finalnavigatio.ui.animal_adapter.Animal
import com.example.myapplication_finalnavigatio.ui.animal_adapter.AnimalAdapter
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment
import com.example.myapplication_finalnavigatio.ui.home.likeAnimals


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentFavouriteBinding =
        FragmentFavouriteBinding::inflate
    private lateinit var vm: FavouriteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm = FavouriteViewModel()
        visibilityPlaceHolder(binding.constraintPlaseHolder, binding.recyclerViewLike)
        val adapter = AnimalAdapter()
        binding.bottomAddAnimals.setOnClickListener {
            vm.navigateToo(root = binding.root)
        }
        val deleteClick = { animal: Animal ->
            likeAnimals.remove(element = animal)
            adapter.submitList(likeAnimals.toList())
            if (likeAnimals.size == 0) {
                binding.constraintPlaseHolder.visibility = View.VISIBLE
                binding.recyclerViewLike.visibility = View.INVISIBLE
            }
        }
        adapter.deleteClick = deleteClick
        adapter.submitList(likeAnimals.toList())
        binding.recyclerViewLike.adapter = adapter
        binding.recyclerViewLike.layoutManager = LinearLayoutManager(requireContext())
        super.onViewCreated(view, savedInstanceState)
    }

    private fun visibilityPlaceHolder(constraint: ConstraintLayout, recyclerView: RecyclerView) =
        if (likeAnimals.size !== 0) {
            constraint.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
        } else {
            constraint.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        }
}