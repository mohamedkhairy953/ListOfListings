package com.khairy.listing_list.model.remote


import com.khairy.listing_list.model.response.ProductsResponseDto
import retrofit2.http.GET

/**
 * Created by Khairy at 8/20/2019
 * mohamedsallam953@gmail.com
 */

interface ProductsWebServices {
    @GET("shehabic-work/514884a074f872f8cdc30ab71a4703df/raw/2c94601c85abaca7b7abaea33ae4a05b89da1970/foodora-sample-products.json")
    suspend fun fetchProducts(): ProductsResponseDto


}