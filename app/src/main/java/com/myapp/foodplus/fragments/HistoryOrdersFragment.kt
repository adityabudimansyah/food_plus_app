package com.myapp.foodplus.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.myapp.foodplus.R
import com.myapp.foodplus.activities.HistoryDetailActivity

class HistoryOrdersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cvHistoryItem: CardView = view.findViewById(R.id.cvHistoryItem)
        cvHistoryItem.setOnClickListener {
            val intent = Intent(context, HistoryDetailActivity::class.java)
            startActivity(intent)
        }
    }

}