package com.example.myapplication_finalnavigatio.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.ui.animalAdapter.Animal

class HomeFragmentViewModel : ViewModel() {

    private val animalsLiveData = MutableLiveData(
        mutableListOf(
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
                "Это базовое животное, рост до 1 метра, весом до 50 кг, умеет только любить и никак иначе"
            ),
            Animal(
                "https://mirinteresen.net/uploads/posts/2018-03/1520833299_1-1.jpeg",
                "Медоед",
                "Бессметрное животное, съест любого"
            ),
            Animal(
                "https://krasivosti.pro/uploads/posts/2021-07/1626139820_44-krasivosti-pro-p-oskal-panteri-semeistvo-koshachikh-krasivo-47.jpg",
                "Пантера",
                "Обычная кошка необычных размеров"
            ),
            Animal(
                "https://thongabeachlodge.co.za/wp-content/uploads/sites/15/2019/04/hippo_shutterstock_1098821045.jpg",
                "Бегемот",
                "Здоровяк, который любит арбузики"
            ),
        )
    )
    val animalsLive: LiveData<MutableList<Animal>> = animalsLiveData

    private fun addAnimal(animal: Animal) {
        animalsLiveData.value?.add(animal)
    }

    val likeClick = { animal: Animal ->
        animal.like = true
        likeAnimals.add(animal)
        Unit
    }

    fun itemClick(view: View): (Animal) -> Unit {
        val bundle = Bundle()
        val itemClick = { animal: Animal ->
            bundle.putString(imgURLKey, animal.imgURL)
            bundle.putString(keyName, animal.name)
            Navigation.findNavController(view).navigate(R.id.action_home2_to_details, bundle)
        }
        return itemClick
    }

    fun addUserAnimal(arguments: Bundle?) {
        if (arguments?.getBoolean(booleanKey) == true) {
            val animal = Animal(
                arguments.getString(imgURLKey),
                arguments.getString(keyName),
                arguments.getString(descriptionKey)
            )
            addAnimal(animal)
        }
    }

    fun goToPreviewFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_addAnimal)
    }
}

