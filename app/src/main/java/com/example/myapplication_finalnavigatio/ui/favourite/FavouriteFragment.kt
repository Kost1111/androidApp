package com.example.myapplication_finalnavigatio.ui.favourite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_finalnavigatio.databinding.FragmentFavouriteBinding
import com.example.myapplication_finalnavigatio.ui.animalAdapter.Animal
import com.example.myapplication_finalnavigatio.ui.animalAdapter.AnimalAdapter
import com.example.myapplication_finalnavigatio.ui.home.likeAnimals


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: FavouriteViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vm = FavouriteViewModel()
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        visibilityPlaceHolder(binding.constraintPlaseHolder, binding.recyclerViewLike)
        val adapter = AnimalAdapter()
        binding.bottomAddAnimals.setOnClickListener {
          vm.navigateToo(root = root)
        }

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
        binding.recyclerViewLike.layoutManager = LinearLayoutManager(requireContext())
        return root
    }

    private fun visibilityPlaceHolder(constraint: ConstraintLayout, recyclerView: RecyclerView) =
        if (likeAnimals.size !== 0) {
            constraint.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
        } else {
            constraint.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}