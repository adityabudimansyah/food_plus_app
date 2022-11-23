package com.myapp.foodplus.adaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.foodplus.R
import com.myapp.foodplus.models.RestaurantData

class RestaurantFavoriteAdapter (private val context: Context, private val restaurantData: List<RestaurantData>, val listener: (RestaurantData) -> Unit)
    : RecyclerView.Adapter<RestaurantFavoriteAdapter.RestaurantViewHolder>(){

    class RestaurantViewHolder (view: View): RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.tvRestaurantName)
        val highlight = view.findViewById<TextView>(R.id.tvRestaurantHighlight)
        val image = view.findViewById<ImageView>(R.id.ivRestaurantImage)

        fun bindView(restaurantData: RestaurantData, listener: (RestaurantData) -> Unit) {
            name.text = restaurantData.name
            highlight.text = restaurantData.highlight
            image.setImageResource(restaurantData.image)

            itemView.setOnClickListener {
                listener(restaurantData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_restaurant_favorite, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bindView(restaurantData[position], listener)
    }

    override fun getItemCount(): Int = restaurantData.size

}