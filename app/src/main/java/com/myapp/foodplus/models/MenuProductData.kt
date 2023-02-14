package com.myapp.foodplus.models

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class MenuProductData(
    val name: String,
    val stock: Int,
    val price: Int,
    val image: Int
): Parcelable
