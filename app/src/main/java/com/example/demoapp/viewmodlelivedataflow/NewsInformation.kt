package com.example.demoapp.viewmodlelivedataflow

import com.google.gson.annotations.SerializedName
import java.util.Date

data class NewsInformation(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: ArrayList<Articles>

)

data class  Articles(
    @SerializedName("source")
    val source: Source = Source(),
    @SerializedName("author")
    val author:String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String,
    @SerializedName("publishAt")
    val publishAt: Date,
    @SerializedName("content")
    val content: String

)

data class Source(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
    )