package com.example.myapplication_finalnavigatio.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.database.LikedAnimalsInfo
import com.example.myapplication_finalnavigatio.database.entites.LikedAnimalRepository
import com.example.myapplication_finalnavigatio.ui.animal_adapter.Animal
import com.example.myapplication_finalnavigatio.utils.*
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val likedAnimalRepository: LikedAnimalRepository) :
    ViewModel() {

    private val animalsLiveData = MutableLiveData(animals)
    val animalsLive: LiveData<MutableList<Animal>> = animalsLiveData

    private val mLikedAnimals = MutableLiveData<List<LikedAnimalsInfo>>()
    val likedAnimals: LiveData<List<LikedAnimalsInfo>> = mLikedAnimals


    private fun insertNewLikedAnimals(animal: Animal) {
        viewModelScope.launch {
            likedAnimalRepository.insertNewLikedAnimals(animal.toLikedAnimalsDbEntity())
        }
    }

    private fun addAnimal(animal: Animal) {
        animalsLiveData.value?.add(animal)
    }

    val likeClick = { animal: Animal ->
        animal.like = true
        likeAnimals.add(animal)
        insertNewLikedAnimals(animal = Animal(animal.imgURL, animal.name, animal.description))
        Unit
    }


    fun itemClick(view: View): (Animal) -> Unit {
        val bundle = Bundle()
        val itemClick = { animal: Animal ->
            bundle.apply {
                putString(imgURLKey, animal.imgURL)
                putString(keyName, animal.name)
            }
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

