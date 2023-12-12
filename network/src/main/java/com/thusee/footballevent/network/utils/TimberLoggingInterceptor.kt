package com.thusee.footballevent.network.utils

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class TimberLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        Timber.d("Sending request: ${request.url}")
        Timber.d("Received response for ${response.request.url} response $response")

        return response
    }
}