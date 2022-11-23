package com.myapp.foodplus.models

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class MenuData (
    val name: String,
    val stock: Int,
    val price: Int,
    val normalPrice: Int,
    val image: Int,
    val description: String,
    val expireDate: String
) : Parcelable