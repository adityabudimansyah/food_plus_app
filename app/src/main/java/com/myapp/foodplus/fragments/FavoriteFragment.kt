package com.myapp.foodplus.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapp.foodplus.R
import com.myapp.foodplus.activities.MenuDetailActivity
import com.myapp.foodplus.activities.RestaurantDetailActivity
import com.myapp.foodplus.adaters.RestaurantAdapter
import com.myapp.foodplus.adaters.RestaurantFavoriteAdapter
import com.myapp.foodplus.models.RestaurantData

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure Restaurants Recycler View
        val restaurantDataList = ArrayList<RestaurantData>()
        restaurantDataList.addAll(listRestaurantData)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_restaurant)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.adapter = RestaurantFavoriteAdapter(requireContext(), restaurantDataList) {
            val intent = Intent (context, RestaurantDetailActivity::class.java)
            intent.putExtra("OBJECT_INTENT", it)
            startActivity(intent)
        }
    }

    private val listRestaurantData: ArrayList<RestaurantData>
        get() {
            val dataName = resources.getStringArray(R.array.data_restaurant_name)
            val dataHighlight = resources.getStringArray(R.array.data_restaurant_highlight)
            val dataPhoto = resources.obtainTypedArray(R.array.data_restaurant_photo)
            val dataDesc = resources.getStringArray(R.array.data_restaurant_description)

            val lists = ArrayList<RestaurantData>()
            for (i in 1..2) {
                for (i in dataName.indices) {
                    val restaurantData = RestaurantData(
                        dataName[i], dataHighlight[i], dataPhoto.getResourceId(i, -1), dataDesc[i], 0.0, 0.0
                    )
                    lists.add(restaurantData)
                }
            }
            return lists
        }

}