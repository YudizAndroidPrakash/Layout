package com.example.demoapp.jsondemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.databinding.AdapterListOfProductsBinding
import com.squareup.picasso.Picasso

//class AdapterProductsData(
//    val context: Context,
//    private val productList: ArrayList<Products>,
//    private val listener: IProductsRVAdapter
//) : RecyclerView.Adapter<AdapterProductsData.ViewHolder>() {
//    private  lateinit var binding : AdapterListOfProductsBinding
class AdapterProductsData(
    val context: Context,
    private val productList: ArrayList<Products>,
    private val onClick: (lst: Products) -> Unit
) : RecyclerView.Adapter<AdapterProductsData.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.adapter_list_of_products, parent, false)
        )
//        view.itemView.setOnClickListener {
//
//            listener.onItemClick(productList[view.adapterPosition])
//        }
        view.itemView.setOnClickListener {
            onClick.invoke(productList[view.adapterPosition])
        }

        return view
//        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentProductPosition = productList[position]
        holder.tvProductTitle.text = currentProductPosition.title
        holder.tvProductDescription.text = currentProductPosition.category
        holder.tvProductPrice.text = currentProductPosition.price.toString()
        Picasso.get().load(currentProductPosition.thumbnail).placeholder(R.drawable.imgavatar)
            .into(holder.imgThumbnail)

    }

    override fun getItemCount() = productList.size

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        productList[position].apply {
//          holder.tvProductTitle.text = title
//            holder.tvProductDescription.text = category
//            holder.tvProductPrice.text = price.toString()
//            Picasso.get().load(thumbnail).into(holder.imgThumbnail)
//        }


//        val currentProductPosition = productList[position]
//        holder.tvProductTitle.text = currentProductPosition.title
//        holder.tvProductDescription.text = currentProductPosition.category
//        holder.tvProductPrice.text = currentProductPosition.price.toString()
//
//
//
//        Picasso.get().load(currentProductPosition.thumbnail).into(holder.imgThumbnail)


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvProductTitle: TextView
        var tvProductPrice: TextView
        var tvProductDescription: TextView
        var imgThumbnail: ImageView

        init {
            tvProductTitle = itemView.findViewById(R.id.tv_product_title)
            tvProductPrice = itemView.findViewById(R.id.tv_product_price)
            tvProductDescription = itemView.findViewById(R.id.tv_product_description)
            imgThumbnail = itemView.findViewById(R.id.img_product_thumbnail)

        }

    }

}


interface IProductsRVAdapter {
    fun onItemClick(products: Products)
}


//Using Binding


class AdapterProductsDataOne(
    val context: Context, private val productList: ArrayList<Products>,
    private val onClick: (lst: Products) -> Unit
) : RecyclerView.Adapter<AdapterProductsDataOne.ViewHolder>() {

    inner class ViewHolder(binding: AdapterListOfProductsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterProductsDataOne.ViewHolder {
        val binding = AdapterListOfProductsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding.root.setOnClickListener {
            //on adapter click get the position the particular data pending
//            onClick.invoke(productList[])
        }
        return  ViewHolder(binding)
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: AdapterProductsDataOne.ViewHolder, position: Int) {
    }


}