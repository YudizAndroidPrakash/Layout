package com.example.demoapp.jsondemo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserPost(
    @SerializedName("id")
    @Expose
    val Id : Int = 0,
    @Expose
    @SerializedName("post_title")
    val Title : String = "",
    @SerializedName("post_body")
    @Expose
    val body : String = "",
    @SerializedName("post_user_id")
    @Expose(serialize = true, deserialize = false)
    val userId : Int = 0,
    @SerializedName("post_tags")
    val tags : List<String> = listOf(),
    @SerializedName("post_reactions")
    @Expose(serialize = false, deserialize = true)
    val reactions : Int = 0
)


