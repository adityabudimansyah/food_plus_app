package com.myapp.foodplus.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class RestaurantData (
        val name: String,
        val highlight: String,
        val image: Int,
        val description: String,
        val lat: Double,
        val long: Double,
) : Parcelable
