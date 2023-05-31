package com.example.demoapp.jsondemo

import com.google.gson.annotations.SerializedName

data class ProductData(
    @SerializedName("products")
    val products : ArrayList<Products>,
    val total : Int,
    val skip : Int,
    val limit : Int
)