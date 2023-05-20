package com.example.myapplication_finalnavigatio.ui.details

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication_finalnavigatio.api.FactApi
import com.example.myapplication_finalnavigatio.api.model.CatFacts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {


    private val mLoadingStateLiveData = MutableLiveData<Boolean>()
    val loadingStateLiveData: LiveData<Boolean> = mLoadingStateLiveData

    private var mLoadingFact = MutableLiveData<String>()
    val loadingFact: LiveData<String> = mLoadingFact

    fun loadFacts() {
        mLoadingStateLiveData.value = true
        val api = FactApi.create().getFact()
        api.enqueue(object : Callback<CatFacts> {
            override fun onResponse(call: Call<CatFacts>, response: Response<CatFacts>) {
                mLoadingStateLiveData.value = false
                mLoadingFact.value = response.body()?.fact
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<CatFacts>, t: Throwable) {
                mLoadingStateLiveData.value = false
                mLoadingFact.value = t.message

            }
        })
    }
}