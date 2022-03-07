package com.khairy.listing_list.di

import com.khairy.listing_list.model.remote.ProductsWebServices
import com.khairy.listing_list.model.repo.ProductsRepo
import com.khairy.listing_list.presentation.viewmodel.ProductsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


var featureProductsModule = module {
    factory { provideApi(get()) }
    single { provideRepo(get()) }
    viewModel { provideViewModel(get()) }
}

fun provideRepo(get: ProductsWebServices) = ProductsRepo(get)

fun provideViewModel(get: ProductsRepo) = ProductsViewModel(get)


fun provideApi(retrofit: Retrofit): ProductsWebServices =
    retrofit.create(ProductsWebServices::class.java)
