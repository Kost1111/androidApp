package com.example.myapplication_finalnavigatio.database.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "table1",
    indices = [Index("id")]
)
data class LikedAnimalsDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imgURL: String,
    val name: String,
    val description: String,
    val like: Boolean
)