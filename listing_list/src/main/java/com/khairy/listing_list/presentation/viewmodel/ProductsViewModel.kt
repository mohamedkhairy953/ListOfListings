package com.khairy.listing_list.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khairy.core.helpers.base.BaseResponseWrapper
import com.khairy.core.helpers.base.BaseViewModel
import com.khairy.core.test_utils.EspressoIdlingResource
import com.khairy.listing_list.domain.ProductsResponse
import com.khairy.listing_list.model.repo.ProductsRepo
import kotlinx.coroutines.launch


class ProductsViewModel  constructor(
    private val repo: ProductsRepo
) : BaseViewModel() {
    val getListingsResultLD = MutableLiveData<ProductsResponse>()

    init {
        getListingList()
    }


    fun getListingList() {
        viewModelScope.launch {
            dataLoading.value = true
            EspressoIdlingResource.increment()
            val resp = repo.fetchProducts()
            EspressoIdlingResource.decrement()
            dataLoading.value = false
            when (resp) {
                is BaseResponseWrapper.Failed -> showErrorMessageEvent.value = resp.message
                BaseResponseWrapper.NetworkError -> showNoNetworkScreenEvent.value = true
                BaseResponseWrapper.ServerError -> showServerIssueEvent.value = true
                is BaseResponseWrapper.Success<*> -> getListingsResultLD.value =
                    resp.data as ProductsResponse?
            }
        }
    }
}