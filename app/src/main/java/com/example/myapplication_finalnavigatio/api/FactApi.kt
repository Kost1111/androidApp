package com.example.myapplication_finalnavigatio.api

import com.example.myapplication_finalnavigatio.api.model.CatFacts
import com.example.myapplication_finalnavigatio.utils.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FactApi {

    @GET("fact")
    fun getFact(): Call<CatFacts>

    companion object {
        fun create(): FactApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(FactApi::class.java)
        }

    }
}