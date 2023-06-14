package com.example.myapplication_finalnavigatio.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication_finalnavigatio.database.LikedAnimalsInfo
import com.example.myapplication_finalnavigatio.database.entites.LikedAnimalRepository
import com.example.myapplication_finalnavigatio.ui.animal_adapter.LikedAnimalsItemListener
import kotlinx.coroutines.launch

class FavouriteViewModel(private val likedAnimalRepository: LikedAnimalRepository) : ViewModel() {
    private val mLikedAnimals = MutableLiveData<List<LikedAnimalsInfo>>()
    val likedAnimals: LiveData<List<LikedAnimalsInfo>> = mLikedAnimals

    init {
        getAllLikedAnimal()
    }

    val likedAnimalsItemListener = object : LikedAnimalsItemListener {
        override fun getInfoAboutLikedAnimal(id: Int) {
//            val likedAnimaId = mLikedAnimals.value?.indexOfFirst { it.id == id }
//            if (likedAnimaId == -1) return
//            val likedAnimalsInfo = mLikedAnimals.value?.get(likedAnimaId!!)
//            println(likedAnimalsInfo)
        }

        override fun removeLikedAnimals(id: Int) {
            viewModelScope.launch {
                likedAnimalRepository.removeLikedAnimals(id)
            }
        }
    }


    private fun getAllLikedAnimal() {
        viewModelScope.launch {
            mLikedAnimals.value = likedAnimalRepository.getAllLikedAnimals()
        }
    }
}