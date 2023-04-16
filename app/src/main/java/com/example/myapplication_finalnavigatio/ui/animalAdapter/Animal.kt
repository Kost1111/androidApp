package com.example.myapplication_finalnavigatio.ui.animalAdapter

import androidx.recyclerview.widget.DiffUtil

data class Animal(
    val imgURL: String?,
    val name: String?,
    val description: String?,
    var like: Boolean = false
)



