package com.example.demoapp.jsondemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ListOfProductsActivity : AppCompatActivity(), IProductsRVAdapter {
    private lateinit var rvProductsData: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_products)

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productsApi = retrofit.create(ProductServiceInterface::class.java)
        val call = productsApi.productList()
        Log.d("call", call.toString())

        rvProductsData = findViewById(R.id.rv_product_list)

        call.enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.code() != 200) {
                    Toast.makeText(this@ListOfProductsActivity, "check your internet connection", Toast.LENGTH_SHORT).show()
                    return
                }
                val body = response.body()!!.products
                System.out.println(body)
                val adapter = AdapterProductsData(this@ListOfProductsActivity, body!!, this@ListOfProductsActivity)
                rvProductsData.adapter = adapter
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                System.out.println(t)
            }


        })


//        call.enqueue(object : Callback<ProductData>{
//            override fun onResponse(
//                call: Call<ProductData>,
//                response: Response<ProductData>
//            ) {
//                val body : ProductData = response.body()
//
//                System.out.println(body)
//                val adapter = AdapterProductsData(this@ListOfProductsActivity,body,this@ListOfProductsActivity)
//                rvProductsData.adapter = adapter
//
//                System.out.println(response.body())
//            }
//            override fun onFailure(call: Call<ProductData>, t: Throwable) {
//                System.out.println(t)
//            }
//
//        })

    }



    override fun onItemClick(products : Products) {
        System.out.println(products)
//        val dataOfProduct = Products(products.id,products.description,products.description,products.price,products.discountPercentage,products.rating,products.stock,products.brand,products.category,products.thumbnail,products.images)
        val intent = Intent(this,ProductDetailActivity::class.java)
        intent.putExtra("details",products)
        startActivity(intent)



    //        val data = products
//                    Log.d("data",products.toString())
//
//
//        val bundle = Bundle()
//        bundle.putSerializable("details",products)
//        val intent = Intent(this,ProductDetailActivity::class.java)
//        intent.putExtra("productDetails",products)


    }
}