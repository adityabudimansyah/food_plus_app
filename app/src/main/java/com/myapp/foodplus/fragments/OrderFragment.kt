package com.myapp.foodplus.fragments

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.myapp.foodplus.R
import com.myapp.foodplus.adaters.PagerOrdersAdapter
import com.myapp.foodplus.adaters.PagerRestaurantDetailAdapter
import com.myapp.foodplus.databinding.ActivityRestaurantDetailBinding
import com.myapp.foodplus.models.RestaurantData

class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // configure tab layout and viewpager
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        viewPager.adapter = PagerOrdersAdapter(context as FragmentActivity)
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> { "Cart" }
                1 -> { "On Going" }
                2 -> { "History" }
                else -> { throw Resources.NotFoundException("Position not found")}
            }
        }.attach()

    }

}