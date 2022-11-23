package com.myapp.foodplus.adaters

import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myapp.foodplus.fragments.*

class PagerOrdersAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> { CartOrderFragment() }
            1 -> { OnGoingOrdersFragment() }
            2 -> { HistoryOrdersFragment() }
            else -> { throw  Resources.NotFoundException("Position not found")}
        }
    }

}