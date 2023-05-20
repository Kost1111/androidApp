package com.example.myapplication_finalnavigatio.ui.add_animal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication_finalnavigatio.ui.animal_preview.AnimalPresentationModel

class AddAnimalViewModel: ViewModel() {
    private val mAnimalLiveData = MutableLiveData<AnimalPresentationModel>()
    val animalLiveData: LiveData<AnimalPresentationModel> = mAnimalLiveData


}