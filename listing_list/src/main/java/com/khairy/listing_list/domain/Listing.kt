package com.khairy.listing_list.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Listing(
    val price: String?,
    val name: String?,
    val imageIds: List<String>?,
    val imageUrls: List<String>?,
    val imageUrlsThumbs: List<String>?,
) : Parcelable