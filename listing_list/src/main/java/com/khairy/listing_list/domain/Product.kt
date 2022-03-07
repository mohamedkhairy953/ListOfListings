package com.khairy.listing_list.domain


import com.google.gson.annotations.SerializedName

data class Product(
    val brand: String?,
    val id: String?,
    val image: String?,
    val isActive: Boolean?,
    val name: String?,
    val currentPrice: Double?,
    val originalPrice: Double?,
    val Currency: String?,
)