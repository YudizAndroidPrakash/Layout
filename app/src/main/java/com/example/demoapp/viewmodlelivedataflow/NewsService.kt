package com.example.demoapp.viewmodlelivedataflow


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("/everything?apiKey=9052eadf429344b7926a2d7a0c8c25bc")
    suspend fun newDetails(@Query("q") name : String) : Response<NewsInformation>



    companion object {
        var newsService : NewsService? = null
        fun getInstance() : NewsService {

            if(newsService == null){
                val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build()
                newsService = retrofit.create(NewsService::class.java)

            }
            return newsService!!
        }
    }

}