package com.myapp.foodplus.adaters

import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myapp.foodplus.fragments.AboutRestaurantFragment
import com.myapp.foodplus.fragments.MenuRestaurantFragment

class PagerRestaurantDetailAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> { MenuRestaurantFragment() }
            1 -> { AboutRestaurantFragment() }
            else -> { throw  Resources.NotFoundException("Position not found")}
        }
    }

}