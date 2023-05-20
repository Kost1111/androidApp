package com.example.myapplication_finalnavigatio.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication_finalnavigatio.database.entites.LikedAnimalsDBEntity


@Dao
interface LikedAnimalsDAO {

    @Insert(entity = LikedAnimalsDBEntity::class)
    fun insertNewLikedAnimals(likedAnimalsDBEntity: LikedAnimalsDBEntity)

    @Query("SELECT table1.id, name, imgURL, description FROM table1")
    fun getAllLikedAnimals(): List<LikedAnimalsInfo>

    @Query("DELETE FROM table1 WHERE id = :likedId ")
    fun deleteLikedAnimal(likedId: Int)
}