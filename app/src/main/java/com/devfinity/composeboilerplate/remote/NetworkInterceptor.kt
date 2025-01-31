package com.devfinity.composeboilerplate.remote

import com.devfinity.composeboilerplate.persistance.prefs.SharedPrefsRepository
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    private val prefRepository: SharedPrefsRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val accessToken = prefRepository.getAccessToken()
        if (accessToken.isNotEmpty()) {
            requestBuilder.addHeader("access-token", accessToken)
        }
        val response = chain.proceed(requestBuilder.build())
        val responseBody = response.body
        val responseString = responseBody.string()

        val contentType = responseBody.contentType()
        return response.newBuilder().body(responseString.toResponseBody(contentType))
            .build()

    }
}