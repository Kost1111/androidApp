package com.example.myapplication_finalnavigatio.ui.favourite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.databinding.FragmentFavouriteBinding
import com.example.myapplication_finalnavigatio.ui.animalAdapter.Animal
import com.example.myapplication_finalnavigatio.ui.animalAdapter.AnimalAdapter
import com.example.myapplication_finalnavigatio.ui.home.likeAnimals


class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        visibilityPlaseHolder(binding.constraintPlaseHolder, binding.recyclerViewLike)
        binding.bottomAddAnimals.setOnClickListener {
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_favourites_to_navigation_home)
        }
        val adapter = AnimalAdapter()
        val deleteClick = { animal: Animal ->
            likeAnimals.remove(animal)
            adapter.submitList(likeAnimals.toList())
            if (likeAnimals.size == 0) {
                binding.constraintPlaseHolder.visibility = View.VISIBLE
                binding.recyclerViewLike.visibility = View.INVISIBLE
            }
        }
        adapter.deleteClick = deleteClick
        adapter.submitList(likeAnimals.toList())
        binding.recyclerViewLike.adapter = adapter
        binding.recyclerViewLike.layoutManager = LinearLayoutManager(activity)
        return root
    }

    private fun visibilityPlaseHolder(constraint: ConstraintLayout, recyclerView: RecyclerView) {
        if (likeAnimals.size !== 0) {
            constraint.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
        } else {
            constraint.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}