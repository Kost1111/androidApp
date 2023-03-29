package com.example.myapplication_finalnavigatio.ui.Favourite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_finalnavigatio.AnimalAdapter.Animal
import com.example.myapplication_finalnavigatio.AnimalAdapter.AnimalAdapter
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.ui.Home.likeAnimals


class Favourite : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewLike)
        val constraint: ConstraintLayout = view.findViewById(R.id.constraintPlaseHolder)

        visibilityPlaseHolder(constraint, recyclerView)

        val button: Button = view.findViewById(R.id.bottomAddAnimals)
        button.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_navigation_favourites_to_navigation_home)
        }

        val adapter = AnimalAdapter()
        val deleteClick = { animal: Animal ->
            likeAnimals.remove(animal)
            adapter.submitList(likeAnimals.toList())
        }

        adapter.deleteClick = deleteClick
        adapter.submitList(likeAnimals.toList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        return view
    }

    fun visibilityPlaseHolder(constraint: ConstraintLayout, recyclerView: RecyclerView) {
        if (likeAnimals.size !== 0) {
            constraint.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
        } else {
            constraint.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        }
    }

}