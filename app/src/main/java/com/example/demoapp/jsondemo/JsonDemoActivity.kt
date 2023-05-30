package com.example.demoapp.jsondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.demoapp.R
import com.google.gson.Gson
import org.json.JSONObject

class JsonDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_demo)
    val post = "{\"id\":6," +
            "\"title\":\"Dave wasn't exactly sure how he had ended up\",\"body\":\"Dave wasn't exactly sure how he had ended up in this predicament. He ran through all the events that had lead to this current situation and it still didn't make sense. He wanted to spend some time to try and make sense of it all, but he had higher priorities at the moment. The first was how to get out of his current situation of being naked in a tree with snow falling all around and no way for him to get down.\",\"userId\":47,\"tags\":[\"english\",\"classic\",\"american\"],\"reactions\":3}"
        val userPost = "{\"id\":6," +
                "\"post_title\":\"Dave wasn't exactly sure how he had ended up\",\"post_body\":\"Dave wasn't exactly sure how he had ended up in this predicament. He ran through all the events that had lead to this current situation and it still didn't make sense. He wanted to spend some time to try and make sense of it all, but he had higher priorities at the moment. The first was how to get out of his current situation of being naked in a tree with snow falling all around and no way for him to get down.\",\"post_user_id\":47,\"post_tags\":[\"english\",\"classic\",\"american\"],\"post_reactions\":3}"

        val jsonObject  : JSONObject = JSONObject(post)

        findViewById<Button>(R.id.btn_get_json_data).setOnClickListener {
            System.out.println("Id : " + jsonObject.getInt("id"))
            System.out.println("Title : " + jsonObject.getString("title"))
            System.out.println("Body : " + jsonObject.getString("body"))
            System.out.println("UserId : " + jsonObject.getInt("userId"))
            System.out.println("Tags : " + jsonObject.getJSONArray("tags"))
            System.out.println("Reactions : " + jsonObject.getString("reactions"))
        }

        val addPost  =  Gson().fromJson(userPost,UserPost::class.java)
        System.out.println(addPost)

        val tojson = Gson().toJson(addPost)
        System.out.println(tojson)








    }
}