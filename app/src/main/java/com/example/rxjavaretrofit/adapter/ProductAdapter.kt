package com.example.rxjavaretrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.model.ProductItem

class ProductAdapter(
    private val context: Context,
    private var productList: ArrayList<ProductItem>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    fun setDataProducts(productList: ArrayList<ProductItem>) {
        this.productList = productList
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val category: TextView = view.findViewById<TextView>(R.id.tv_category)
        val title: TextView = view.findViewById<TextView>(R.id.tv_title)
        val image: ImageView = view.findViewById<ImageView>(R.id.iv_product)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.category.text = product.category
        holder.title.text = product.title
        Glide.with(context)
            .load(product.image)
            .into(holder.image)

    }
}