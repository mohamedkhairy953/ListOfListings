package com.khairy.listing_list.di

import com.khairy.listing_list.model.remote.ListingListWebService
import com.khairy.listing_list.model.repo.ListingListRepo
import com.khairy.listing_list.presentation.viewmodel.ListingListViewModel
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.ext.getOrCreateScope
import retrofit2.Retrofit


var featureListingListModule = module {
    factory { provideApi(get()) }
    single { provideRepo(get()) }
    viewModel { provideViewModel(get()) }
}

fun provideRepo(get: ListingListWebService) = ListingListRepo(get)

fun provideViewModel(get: ListingListRepo) = ListingListViewModel(get)


fun provideApi(retrofit: Retrofit): ListingListWebService =
    retrofit.create(ListingListWebService::class.java)
