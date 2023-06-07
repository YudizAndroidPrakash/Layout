package com.example.demoapp.viewmodlelivedataflow

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.databinding.NewsItemAdapaterBinding
import com.example.demoapp.viewmodlelivedataflow.model.Article
import com.squareup.picasso.Picasso
import retrofit2.http.Url

class AdapterNewsData(val context: Context,val newsList : ArrayList<Article>) : RecyclerView.Adapter<AdapterNewsData.ViewHolder>() {


//    lateinit var binding : NewsItemAdapaterBinding
    class ViewHolder(val view: NewsItemAdapaterBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflate = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<NewsItemAdapaterBinding>(inflate, R.layout.news_item_adapater,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.view.tvNewsName.text = newsList[position].source.name
           holder.view.newsData = newsList[position]

    }

    override fun getItemCount(): Int  = newsList.size


}


