package com.example.demoapp.viewmodlelivedataflow.model

data class NewsDataModel(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)