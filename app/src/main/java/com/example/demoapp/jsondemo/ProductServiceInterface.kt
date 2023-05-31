package com.example.demoapp.jsondemo


import retrofit2.Call
import retrofit2.http.GET




interface ProductServiceInterface {
    @GET("products")
    fun productList() : Call<ProductData>

}

