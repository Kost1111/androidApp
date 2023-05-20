package com.example.myapplication_finalnavigatio.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_finalnavigatio.Dependencies
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentFavouriteBinding
import com.example.myapplication_finalnavigatio.ui.animal_adapter.Animal
import com.example.myapplication_finalnavigatio.ui.animal_adapter.LikedAnimalAdapter
import com.example.myapplication_finalnavigatio.ui.base_fragment.BaseFragment
import com.example.myapplication_finalnavigatio.utils.likeAnimals


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentFavouriteBinding =
        FragmentFavouriteBinding::inflate
    private val vm by lazy { FavouriteViewModel(Dependencies.statisticRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Dependencies.init(requireContext())
      //  visibilityPlaceHolder(binding.constraintPlaseHolder, binding.recyclerViewLike)

        vm.likedAnimals.observe(viewLifecycleOwner) { allLikedAnimals ->
            val adapter = LikedAnimalAdapter(vm.likedAnimalsItemListener)
            adapter.data = allLikedAnimals.reversed()

            binding.recyclerViewLike.adapter = adapter
            binding.recyclerViewLike.layoutManager = LinearLayoutManager(requireContext())

        }



//        binding.bottomAddAnimals.setOnClickListener {
//            Navigation.findNavController(binding.root)
//                .navigate(R.id.action_navigation_favourites_to_navigation_home)
//        }
//        val deleteClick = { animal: Animal ->
//            likeAnimals.remove(element = animal)
//            adapter.submitList(likeAnimals.toList())
//            if (likeAnimals.size == 0) {
//                binding.apply {
//                    constraintPlaseHolder.visibility = View.VISIBLE
//                    recyclerViewLike.visibility = View.INVISIBLE
//                }
//            }
//        }
//
//        adapter.deleteClick = deleteClick
//        adapter.submitList(likeAnimals.toList())
//        binding.apply {
//            recyclerViewLike.adapter = adapter
//            recyclerViewLike.layoutManager = LinearLayoutManager(requireContext())
//        }
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