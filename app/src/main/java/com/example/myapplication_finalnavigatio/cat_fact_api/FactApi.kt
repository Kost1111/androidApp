package com.example.myapplication_finalnavigatio.cat_fact_api

import com.example.myapplication_finalnavigatio.cat_fact_api.model.CatFacts
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FactApi {

    @GET("fact")
    fun getFact(): Call<CatFacts>

    companion object {

        var BASE_URL = "https://catfact.ninja"

        fun create(): FactApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(FactApi::class.java)
        }

    }
}