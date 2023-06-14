package com.example.myapplication_finalnavigatio.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication_finalnavigatio.database.entites.LikedAnimalsDBEntity


@Dao
interface LikedAnimalsDAO {

    @Insert(entity = LikedAnimalsDBEntity::class)
    fun insertNewLikedAnimals(likedAnimalsDBEntity: LikedAnimalsDBEntity)

    @Query("SELECT * FROM table1")
    fun getAllLikedAnimals(): List<LikedAnimalsInfo>

    @Query("DELETE FROM table1 WHERE id = :likedId ")
    fun deleteLikedAnimal(likedId: Int)
}