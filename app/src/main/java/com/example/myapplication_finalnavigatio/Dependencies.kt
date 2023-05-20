package com.example.myapplication_finalnavigatio

import android.content.Context
import androidx.room.Room
import com.example.myapplication_finalnavigatio.database.AppDatabase
import com.example.myapplication_finalnavigatio.database.entites.LikedAnimalRepository

object Dependencies {

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .build()
    }

    val statisticRepository: LikedAnimalRepository by lazy { LikedAnimalRepository(appDatabase.getLikedAnimal()) }
}