package com.khairy.listing_list.model.response

import com.google.gson.annotations.SerializedName

data class ListingsResponseDto(@SerializedName("results") val lisitngs: List<ListingDto>?)