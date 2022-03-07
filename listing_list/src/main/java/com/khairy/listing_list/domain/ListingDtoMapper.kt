package com.khairy.listing_list.domain

import com.khairy.listing_list.model.response.ProductsResponseDto
import com.khairy.listing_list.model.response.ProductDto

fun ProductsResponseDto.toListingResponse(): ProductsResponse {
    return ProductsResponse(products = products?.map { it.toProduct() })
}

fun ProductDto.toProduct(): Product {
    return Product(
        brand, id, image,
        isActive, name, price?.current, price?.original, price?.currency
    )
}