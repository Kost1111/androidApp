package com.example.myapplication_finalnavigatio.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication_finalnavigatio.database.entites.LikedAnimalsDBEntity

@Database(
    version = 1,
    entities = [
        LikedAnimalsDBEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getLikedAnimal(): LikedAnimalsDAO

}