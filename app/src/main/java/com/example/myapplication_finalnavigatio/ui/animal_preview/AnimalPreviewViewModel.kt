package com.example.myapplication_finalnavigatio.ui.animal_preview

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
import com.example.myapplication_finalnavigatio.utils.booleanKey
import com.example.myapplication_finalnavigatio.utils.descriptionKey
import com.example.myapplication_finalnavigatio.utils.imgURLKey
import com.example.myapplication_finalnavigatio.utils.keyName
import kotlinx.coroutines.launch

class AnimalPreviewViewModel(private val likedAnimalRepository: LikedAnimalRepository) :
    ViewModel() {

    private val mAnimalLiveData = MutableLiveData<AnimalPresentationModel>()
    val animalLiveData: LiveData<AnimalPresentationModel> = mAnimalLiveData

    private val mLikedAnimals = MutableLiveData<List<LikedAnimalsInfo>>()
    val likedAnimals: LiveData<List<LikedAnimalsInfo>> = mLikedAnimals


    fun insertNewLikedAnimals(animal: Animal) {
        viewModelScope.launch {
            likedAnimalRepository.insertNewLikedAnimals(animal.toLikedAnimalsDbEntity())
        }
    }

    fun animalPreview(root: View, name: String?, description: String?, imgURL: String?) {
        if (name == "" || imgURL == "" || description == "") {
            Navigation.findNavController(root)
                .navigate(R.id.action_animalPreview_to_addAnimal)
        } else {
            val bundle2 = Bundle()
            val temp = true
            bundle2.putBoolean(booleanKey, temp)
            bundle2.putString(keyName, name)
            bundle2.putString(imgURLKey, imgURL)
            bundle2.putString(descriptionKey, description)
            Navigation.findNavController(root)
                .navigate(R.id.action_animalPreview_to_navigation_home, bundle2)
        }
    }

    fun loadData(argument: Bundle?) {
        mAnimalLiveData.value?.name = argument?.getString(keyName)
        mAnimalLiveData.value?.description = argument?.getString(imgURLKey)
        mAnimalLiveData.value?.imgURL = argument?.getString(descriptionKey)
    }


}

