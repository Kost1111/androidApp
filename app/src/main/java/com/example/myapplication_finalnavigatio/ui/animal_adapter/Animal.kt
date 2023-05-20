package com.example.myapplication_finalnavigatio.ui.animal_adapter

import com.example.myapplication_finalnavigatio.database.entites.LikedAnimalsDBEntity


data class Animal(
    val imgURL: String?,
    val name: String?,
    val description: String?,
    var like: Boolean = false
) {

    fun toLikedAnimalsDbEntity(): LikedAnimalsDBEntity = LikedAnimalsDBEntity(
        id = 0,
        imgURL = imgURL!!,
        name = name!!,
        description = description!!,
        like = like
    )
}




