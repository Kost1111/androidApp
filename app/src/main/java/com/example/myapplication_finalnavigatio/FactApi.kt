package com.example.myapplication_finalnavigatio

import retrofit2.http.GET

interface FactApi {

    @GET("fact")
    suspend fun getFactApi(): CatFacts

}