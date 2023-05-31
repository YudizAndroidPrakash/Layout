package com.example.demoapp.jsondemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.squareup.picasso.Picasso

class AdapterProductsData(val context: Context, private val productList: ArrayList<Products>, private val listener: IProductsRVAdapter) : RecyclerView.Adapter<AdapterProductsData.ViewHolder>() {




    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tvProductTitle : TextView
        var tvProductPrice : TextView
        var tvProductDescription : TextView
        var imgThumbnail : ImageView

        init {
            tvProductTitle = itemView.findViewById(R.id.tv_product_title)
            tvProductPrice = itemView.findViewById(R.id.tv_product_price)
            tvProductDescription = itemView.findViewById(R.id.tv_product_description)
            imgThumbnail = itemView.findViewById(R.id.img_product_thumbnail)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_list_of_products,parent,false))
        view.itemView.setOnClickListener {

            listener.onItemClick(productList[view.adapterPosition])
        }
        return  view
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           val currentProductPosition  = productList[position]
            holder.tvProductTitle.text = currentProductPosition.title
            holder.tvProductDescription.text = currentProductPosition.category
        holder.tvProductPrice.text = currentProductPosition.price.toString()



    Picasso.get().load(currentProductPosition.thumbnail).into(holder.imgThumbnail)
//        Picasso.with(context).load(currentProductPosition.thumbnail).into(holder.imgThumbnail)
    }
}


interface IProductsRVAdapter{
    fun onItemClick(products: Products)
}