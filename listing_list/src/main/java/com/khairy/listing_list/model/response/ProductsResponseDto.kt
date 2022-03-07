package com.khairy.listing_list.model.response

import com.google.gson.annotations.SerializedName

data class ProductsResponseDto(@SerializedName("items") val products: List<ProductDto>?)