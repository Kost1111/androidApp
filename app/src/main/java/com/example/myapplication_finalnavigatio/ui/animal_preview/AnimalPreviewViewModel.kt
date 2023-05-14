package com.example.myapplication_finalnavigatio.ui.animal_preview

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.myapplication_finalnavigatio.R
import com.example.myapplication_finalnavigatio.ui.home.booleanKey
import com.example.myapplication_finalnavigatio.ui.home.descriptionKey
import com.example.myapplication_finalnavigatio.ui.home.imgURLKey
import com.example.myapplication_finalnavigatio.ui.home.keyName

class AnimalPreviewViewModel : ViewModel() {

    private val resultLiveName = MutableLiveData<String>()
    val resultName: LiveData<String> = resultLiveName

    private val resultLiveImgURL = MutableLiveData<String>()
    val resultImgURL: LiveData<String> = resultLiveImgURL

    private val resultLiveDescription = MutableLiveData<String>()
    val resultDescription: LiveData<String> = resultLiveDescription

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
        resultLiveName.value = argument?.getString(keyName)
        resultLiveImgURL.value = argument?.getString(imgURLKey)
        resultLiveDescription.value = argument?.getString(descriptionKey)

    }
}