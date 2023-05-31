package com.example.demoapp.jsondemo

import java.io.Serializable

data class Products(
    val id: Int,
    val title: String,
    val description : String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Float,
    val stock : Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
) : Serializable


