package com.example.myapplication_finalnavigatio.ui.AnimalPreview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.AnimalAdapter.Animal
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.ui.Home.addAnimals


class AnimalPreview : Fragment() {


    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animal_preview, container, false)

        val name = arguments?.getString("addName")
        val imgURL = arguments?.getString("imgURL")
        val description = arguments?.getString("description")

        val nameView = view.findViewById<TextView>(R.id.textView_namePreview)
        val imgURLView = view.findViewById<ImageView>(R.id.imageViewPreview)
        val descriptionView = view.findViewById<TextView>(R.id.textView_DescriptionPreview)
        val buttonAdd = view.findViewById<Button>(R.id.button_add_animal_into_preview)
        nameView.text = name
        descriptionView.text = description
        Glide.with(imgURLView)
            .load(imgURL)
            .centerCrop()
            .error(R.drawable.error)
            .into(imgURLView)

        buttonAdd.setOnClickListener {
            val addAnimal = Animal(imgURL, name, description)
            addAnimals.add(addAnimal)
            val bundle2 = Bundle()

            var temp = true

            bundle2.putBoolean("bool", temp)
            bundle2.putString("addName", name)
            bundle2.putString("imgURL", imgURL)
            bundle2.putString("description", description)

            Navigation.findNavController(view)
                .navigate(R.id.action_animalPreview_to_navigation_home, bundle2)

            temp = false
        }



        return view

    }


}