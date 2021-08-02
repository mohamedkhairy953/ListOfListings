package com.khairy.listing_list.model.remote


import com.khairy.listing_list.model.response.ListingsResponseDto
import retrofit2.http.GET

/**
 * Created by Khairy at 8/20/2019
 * mohamedsallam953@gmail.com
 */

interface ListingListWebService {
    @GET("default/dynamodb-writer")
    suspend fun getAllListings(): ListingsResponseDto


}