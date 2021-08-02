package com.khairy.listing_list.domain

import com.khairy.listing_list.model.response.ListingDto
import com.khairy.listing_list.model.response.ListingsResponseDto

fun ListingsResponseDto.toListingResponse(): ListingsResponse {
    return ListingsResponse(lisitngs = lisitngs?.map { it.toListing() })
}

fun ListingDto.toListing(): Listing {
    return Listing(price, name, imageIds, imageUrls, imageUrlsThumbs)
}