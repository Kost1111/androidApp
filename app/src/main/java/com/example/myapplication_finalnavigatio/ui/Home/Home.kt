package com.example.myapplication_finalnavigatio.ui.Home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_finalnavigatio.AnimalAdapter.Animal
import com.example.myapplication_finalnavigatio.AnimalAdapter.AnimalAdapter
import com.example.myapplication_finalnavigatio.R

val likeAnimals = mutableListOf<Animal>()
var addAnimals = mutableListOf<Animal>()
val animals = mutableListOf(
    Animal(
        "https://animalreader.ru/wp-content/uploads/2014/02/ezhi_1_1.jpg",
        "Ёжик",
        "Это Ёжик! он обитает в лесах и морях, может быть злым и жестоким, поэтому лучше его не злить, подробное описание" +
                "можно посмотреть нажав на картинку"
    ),
    Animal(
        "https://kipmu.ru/wp-content/uploads/slnmgbgprg-scaled.jpg",
        "Слон",
        "Большой и могучий слон ! Поднимает на бицепс не меньше центнера, крайне уверенное в себе животное " +
                "Если столкнетесь с ним, лучше бегите!"
    ),
    Animal(
        "https://cdn.fishki.net/upload/post/2016/04/30/1937149/277276-frederika.jpg",
        "Крокодил",
        "Такая пасть, что может проглотить и даже не заметить. С легкостью может съесть тигра и даже бегемота" +
                "быть максимально бдительным в случае встречи с ним! "
    ),
    Animal(
        "https://mobimg.b-cdn.net/v3/fetch/77/77589b6e8601d844e1093e6d5ad54e3f.jpeg?w=2000",
        "Тигр",
        "Базовый царь зверей! Всех прекрасней и умее. бегает быстро, кусает больно, красивый!" +
                "но в качестве домашнего животного будет перебор "
    ),
    Animal(
        "https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663115165_57-mykaleidoscope-ru-p-zlaya-kapibara-krasivo-63.jpg",
        "Копибара",
        ""
    ),
    Animal(
        "https://mirinteresen.net/uploads/posts/2018-03/1520833299_1-1.jpeg",
        "Медоед",
        ""
    ),
    Animal(
        "https://krasivosti.pro/uploads/posts/2021-07/1626139820_44-krasivosti-pro-p-oskal-panteri-semeistvo-koshachikh-krasivo-47.jpg",
        "Пантера",
        ""
    ),
    Animal(
        "https://thongabeachlodge.co.za/wp-content/uploads/sites/15/2019/04/hippo_shutterstock_1098821045.jpg",
        "Бегемот",
        ""
    ),
)

class Home : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = Bundle()
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val adapter = AnimalAdapter()
        val itemClick = { animal: Animal ->
            bundle.putString("imgURL", animal.imgURL)
            bundle.putString("name", animal.name)
            Navigation.findNavController(view).navigate(R.id.action_home2_to_details, bundle)

        }
        val likeClick = { animal: Animal ->
            likeAnimals.add(animal)

            Unit
        }

        if (arguments?.getBoolean("bool") == true) {
            animals.add(
                Animal(
                    arguments?.getString("imgURL"),
                    arguments?.getString("addName"),
                    arguments?.getString("description")
                )
            )

            adapter.submitList(animals.toList())
            requireArguments().clear()
        }

        adapter.itemClick = itemClick
        adapter.likeClick = likeClick
        adapter.submitList(animals.toList())
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.setOnClickListener {
            animals.add(Animal("fsfsd", "sfsdsf", "sfwef"))
            adapter.submitList(animals.toList())
        }

        val buttonAdd: Button = view.findViewById(R.id.homeButtonAddAnimal)
        buttonAdd.setOnClickListener {
            adapter.submitList(animals.toList())
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_addAnimal)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        return view
    }


}