package com.khairy.listing_list.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khairy.core.helpers.base.BaseResponseWrapper
import com.khairy.core.helpers.base.BaseViewModel
import com.khairy.listing_list.domain.ListingsResponse
import com.khairy.listing_list.model.repo.ListingListRepo
import kotlinx.coroutines.launch


class ListingListViewModel  constructor(
    private val repo: ListingListRepo
) : BaseViewModel() {
    val getListingsResultLD = MutableLiveData<ListingsResponse>()

    init {
        getListingList()
    }


    private fun getListingList() {
        viewModelScope.launch {
            dataLoading.value = true
            val resp = repo.getListings()
            dataLoading.value = false
            when (resp) {
                is BaseResponseWrapper.Failed -> showErrorMessageEvent.value = resp.message
                BaseResponseWrapper.NetworkError -> showNoNetworkScreenEvent.value = true
                BaseResponseWrapper.ServerError -> showServerIssueEvent.value = true
                is BaseResponseWrapper.Success<*> -> getListingsResultLD.value =
                    resp.data as ListingsResponse?
            }
        }
    }
}