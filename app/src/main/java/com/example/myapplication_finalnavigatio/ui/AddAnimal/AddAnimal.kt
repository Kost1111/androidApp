package com.example.myapplication_finalnavigatio.ui.AddAnimal

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication_finalnavigatio.R


class AddAnimal : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_animal, container, false)

        val editName: EditText = view.findViewById(R.id.textNameAddAnimal)
        val editDescription: EditText = view.findViewById(R.id.text_Description)
        val editImgURL: EditText = view.findViewById(R.id.text_imgURL)
        val button: Button = view.findViewById(R.id.button_AddAnimal)

        button.setOnClickListener {
            val imgURL = editImgURL.text.toString()
            val name = editName.text.toString()
            val description = editDescription.text.toString()



            val bundle = Bundle()
            bundle.putString("addName", name)
            bundle.putString("imgURL", imgURL)
            bundle.putString("description", description)

                Navigation.findNavController(view)
                    .navigate(R.id.action_addAnimal_to_animalPreview, bundle)

        }



        return view
    }


}