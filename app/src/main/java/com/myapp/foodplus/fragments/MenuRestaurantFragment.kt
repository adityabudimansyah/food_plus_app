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
import com.myapp.foodplus.adaters.RestaurantMenuAdapter
import com.myapp.foodplus.models.MenuData

class MenuRestaurantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure Restaurants Recycler View
        val menuDataList = ArrayList<MenuData>()
        menuDataList.addAll(listMenuData)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_restaurant_menu)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.adapter = RestaurantMenuAdapter(requireContext(), menuDataList) {
            val intent = Intent (context, MenuDetailActivity::class.java)
            intent.putExtra("OBJECT_INTENT", it)
            startActivity(intent)
        }

    }

    private val listMenuData: ArrayList<MenuData>
        get() {
            val dataName = resources.getStringArray(R.array.data_menu_name)
            val dataStock = resources.getStringArray(R.array.data_menu_stock)
            val dataPrice = resources.getStringArray(R.array.data_menu_price)
            val dataNormalPrice = resources.getStringArray(R.array.data_menu_normal_price)
            val dataImage = resources.obtainTypedArray(R.array.data_menu_photo)
            val dataDesc = resources.getStringArray(R.array.data_menu_description)
            val dataExpireDate = resources.getStringArray(R.array.data_menu_expire)

            val lists = ArrayList<MenuData>()
            for (i in 1..7 ) { // set data menu restaurant bakery menjadi 5 ke dalam list
                for (i in dataName.indices) {
                    val menuData = MenuData(
                        dataName[i], dataStock[i].toInt(), dataPrice[i].toInt(), dataNormalPrice[i].toInt(),
                                dataImage.getResourceId(i, -1), dataDesc[i], dataExpireDate[i]
                    )
                    lists.add(menuData)
                }
            }
            return lists
        }

}