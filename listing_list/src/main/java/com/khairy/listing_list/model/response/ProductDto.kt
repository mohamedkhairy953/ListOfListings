package com.khairy.listing_list.model.response


import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("is_active")
    val isActive: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Price?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("sku")
    val sku: String?
)