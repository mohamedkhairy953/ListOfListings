package com.khairy.core.helpers.retrofit
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
internal constructor() : Interceptor {
    private var requestBuilder: Request.Builder? = null

    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        requestBuilder = request.newBuilder()

        requestBuilder!!.addHeader("Content-Type", "application/json")
        addLanguageHeader()
        addAuthenticationHeader()

        return chain.proceed(requestBuilder!!.build())

    }

    private fun addLanguageHeader() {

    }

    private fun addAuthenticationHeader() {

    }
}