package com.khairy.listing_list.model.response

import com.google.gson.annotations.SerializedName

data class ListingDto(
    @SerializedName("price") val price: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("uid") val uid: String?,
    @SerializedName("image_ids") val imageIds: List<String>?,
    @SerializedName("image_urls") val imageUrls:  List<String>?,
    @SerializedName("image_urls_thumbnails") val imageUrlsThumbs: List<String>?,
)