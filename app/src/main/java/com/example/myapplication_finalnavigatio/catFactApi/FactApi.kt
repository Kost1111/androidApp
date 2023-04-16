package com.example.myapplication_finalnavigatio.catFactApi

import retrofit2.http.GET

interface FactApi {

    @GET("fact")
    suspend fun getFactApi(): CatFacts

}