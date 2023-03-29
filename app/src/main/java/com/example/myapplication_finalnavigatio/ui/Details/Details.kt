package com.example.myapplication_finalnavigatio.ui.Details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.R


class Details : Fragment() {


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        return view

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgURL = arguments?.getString("imgURL")


        val imageViewAnimal: ImageView = view.findViewById(R.id.imageViewAnimals)

        Glide.with(imageViewAnimal)
            .load(imgURL)
            .centerCrop()
            .error(R.drawable.error)
            .into(imageViewAnimal)

    }


}
