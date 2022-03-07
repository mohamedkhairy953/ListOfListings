package com.khairy.listoflistings

import android.app.Application
import com.khairy.core.di.networkModule
import com.khairy.listing_list.di.featureProductsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ListingsListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ListingsListApp)
            modules(
                listOf(
                    networkModule,
                    featureProductsModule
                )
            )

        }
    }

}