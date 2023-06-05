package com.example.demoapp.jsondemo


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductServiceInterface {
    @GET("products")
    fun productList() : Call<ProductData>

    @GET("products/{id}")
    fun productDetails(@Path("id") id : Int) : Call<Products>
}

