package com.example.demoapp.viewmodlelivedataflow.api

import com.example.demoapp.viewmodlelivedataflow.model.NewsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  NewsServiceProvider {

//    @GET("everything?apiKey=9052eadf429344b7926a2d7a0c8c25bc")
//    @GET("everything?apiKey=9052eadf429344b7926a2d7a0c8c25bc")

    @GET("everything?apiKey=04b7ddbb69b242728a8b5a860356ea50")
    suspend fun getArticles(@Query("q") name : String) : Response<NewsDataModel>



}