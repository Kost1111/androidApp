package com.example.myapplication_finalnavigatio.ui.Details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication_finalnavigatio.FactApi
import com.example.myapplication_finalnavigatio.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Details : Fragment() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        val catFactsText: TextView = view.findViewById(R.id.catFacts)

        val retrofit = Retrofit.Builder().baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val factApi = retrofit.create(FactApi::class.java)

        lifecycleScope.launch(Dispatchers.Main) {
            val fact = factApi.getFactApi().fact
            catFactsText.text = fact
        }
    }
}
