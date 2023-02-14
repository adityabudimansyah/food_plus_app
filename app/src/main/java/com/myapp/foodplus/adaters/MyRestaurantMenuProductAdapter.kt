package com.myapp.foodplus.adaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.foodplus.R
import com.myapp.foodplus.models.MenuProductData

class MyRestaurantMenuProductAdapter(private val productList: ArrayList<MenuProductData>)
    : RecyclerView.Adapter<MyRestaurantMenuProductAdapter.MyRestaurantMenuProductViewHolder>() {

    class MyRestaurantMenuProductViewHolder(view: View): RecyclerView.ViewHolder(view)  {
        val name: TextView = view.findViewById(R.id.tvMenuName)
        val stock: TextView = view.findViewById(R.id.tvStockAmount)
        val price: TextView = view.findViewById(R.id.tvPrice)
        val image: ImageView = view.findViewById(R.id.ivMenuImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRestaurantMenuProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant_product_list, parent, false)
        return MyRestaurantMenuProductViewHolder(view)
    }

    override fun getItemCount(): Int { return productList.size }

    override fun onBindViewHolder(holder: MyRestaurantMenuProductViewHolder, position: Int) {
        val (name, stock, price, image) = productList[position]
        holder.name.text = name
        holder.stock.text = "${stock.toString()} left"
        holder.price.text = "Rp${price.toString()}"
        holder.image.setImageResource(image)
    }
}