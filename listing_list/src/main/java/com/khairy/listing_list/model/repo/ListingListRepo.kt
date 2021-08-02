package com.khairy.listing_list.model.repo

import com.khairy.core.helpers.base.BaseResponseWrapper
import com.khairy.listing_list.domain.toListingResponse
import com.khairy.listing_list.model.remote.ListingListWebService
import retrofit2.HttpException
import java.io.IOException

class ListingListRepo constructor(private val webService: ListingListWebService) {

    suspend fun getListings(): BaseResponseWrapper {
      return  try {
            val allListings = webService.getAllListings()
            BaseResponseWrapper.Success(allListings.toListingResponse())
        } catch (e: HttpException) {
            BaseResponseWrapper.Failed(e.message())
        } catch (e: IOException) {
            BaseResponseWrapper.NetworkError
        } catch (e: Exception) {
            BaseResponseWrapper.ServerError
        }
    }
}
