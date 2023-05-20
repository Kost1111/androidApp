package com.example.myapplication_finalnavigatio.database.entites

import com.example.myapplication_finalnavigatio.database.LikedAnimalsDAO
import com.example.myapplication_finalnavigatio.database.LikedAnimalsInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LikedAnimalRepository(private val likedAnimalsDAO: LikedAnimalsDAO) {

    suspend fun insertNewLikedAnimals(likedAnimalsDBEntity: LikedAnimalsDBEntity) {
        withContext(Dispatchers.IO) {
            likedAnimalsDAO.insertNewLikedAnimals(likedAnimalsDBEntity)
        }
    }

    suspend fun getAllLikedAnimals(): List<LikedAnimalsInfo> {
        return withContext(Dispatchers.IO) {
            return@withContext likedAnimalsDAO.getAllLikedAnimals()
        }
    }

    suspend fun removeLikedAnimals(id: Int) {
        withContext(Dispatchers.IO) {
            likedAnimalsDAO.deleteLikedAnimal(id)
        }
    }

}