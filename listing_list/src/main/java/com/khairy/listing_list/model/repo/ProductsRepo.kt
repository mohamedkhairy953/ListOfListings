package com.khairy.listing_list.model.repo

import com.khairy.core.helpers.base.BaseResponseWrapper
import com.khairy.listing_list.domain.toListingResponse
import com.khairy.listing_list.model.remote.ProductsWebServices
import retrofit2.HttpException
import java.io.IOException

class ProductsRepo constructor(private val webService: ProductsWebServices) {

    suspend fun fetchProducts(): BaseResponseWrapper {
      return  try {
            val resp = webService.fetchProducts()
            BaseResponseWrapper.Success(resp.toListingResponse())
        } catch (e: HttpException) {
            BaseResponseWrapper.Failed(e.message())
        } catch (e: IOException) {
            BaseResponseWrapper.NetworkError
        } catch (e: Exception) {
            BaseResponseWrapper.ServerError
        }
    }
}
