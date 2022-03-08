package com.khairy.core.helpers.retrofit
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/**
 * Interceptor which adds headers from shared preferences according to the added custom headers,
 * Authentication, languageCode and level headers by default.
 * <br></br>
 * when No-Authentication or Single-Language header is set to true add Authentication and multi
 * language headers from prefs
 */
class MyServiceInterceptor
internal constructor(private val context: Context) : Interceptor {
    private var requestBuilder: Request.Builder? = null

    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        requestBuilder = request.newBuilder()
        if (hasNetwork(context)!!)
            requestBuilder?.header("Cache-Control", "public, max-age=" + 5)
        else
            requestBuilder?.header(
                "Cache-Control",
                "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
            )


        requestBuilder!!.addHeader("Content-Type", "application/json")
        addLanguageHeader()
        addAuthenticationHeader()

        return chain.proceed(requestBuilder!!.build())

    }

    private fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    private fun addLanguageHeader() {

    }

    private fun addAuthenticationHeader() {

    }
}