package com.khairy.core.di

import android.content.Context
import android.net.TrafficStats
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khairy.core.BuildConfig
import com.khairy.core.helpers.retrofit.MyServiceInterceptor
import com.khairy.core.helpers.retrofit.NullOnEmptyConverterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideServiceInterceptor(androidContext()) }
    single { provideOkHttpClient(get(), get(),androidContext()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

fun provideServiceInterceptor(context: Context): MyServiceInterceptor {
    return MyServiceInterceptor(context)
}


fun provideOkHttpClient(
    myServiceInterceptor: MyServiceInterceptor,
    interceptor: HttpLoggingInterceptor,
    context: Context
): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(myServiceInterceptor)
    builder.cache(Cache(context.externalCacheDir!!, 1024 * 1024 * 10))
    TrafficStats.setThreadStatsTag(0x200)
    builder.connectTimeout(120, TimeUnit.SECONDS)
    builder.readTimeout(120, TimeUnit.SECONDS)
    builder.addNetworkInterceptor(interceptor)
    return builder.build()
}

fun provideGson(): Gson {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .setPrettyPrinting()
        .setLenient()
    return gsonBuilder.create()
}

fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(ScalarsConverterFactory.create()) //the ordering is importing, we must but ScalersConverter before Gson
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    } else
        HttpLoggingInterceptor()
}