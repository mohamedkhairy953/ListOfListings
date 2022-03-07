package com.khairy.listing_list.model.response


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("current")
    val current: Double?,
    @SerializedName("original")
    val original: Double?
)