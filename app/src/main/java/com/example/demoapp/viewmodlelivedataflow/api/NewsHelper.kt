package com.example.demoapp.viewmodlelivedataflow.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsHelper {
    private const val BASE_URL = "https://newsapi.org/v2/"

    fun getInstance() : Retrofit {

        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}