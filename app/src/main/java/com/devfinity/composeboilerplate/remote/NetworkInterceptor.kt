package com.devfinity.composeboilerplate.remote

import com.devfinity.composeboilerplate.errors.FailedResponseException
import com.devfinity.composeboilerplate.errors.InvalidResponseException
import com.devfinity.composeboilerplate.errors.ResponseErrorException
import com.devfinity.composeboilerplate.persistance.prefs.local.SharedPrefsLocal
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    private val prefRepository: SharedPrefsLocal
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
        handleError(response, responseString)
        val contentType = responseBody.contentType()
        return response.newBuilder().body(responseString.toResponseBody(contentType))
            .build()
    }

    // TODO:: update this is future to handle error and refresh token
    private fun handleError(response: Response, responseString: String?) {
        if (response.code in 400..599) {
            throw FailedResponseException(response.code,"Request failed with status code: ${response.code}")
        }

        if (responseString != null && responseString.contains("error")) {
            throw ResponseErrorException("Response contains an error: $responseString")
        }

        if (response.body.contentLength() <= 0) {
            throw InvalidResponseException("Response body is empty.")
        }
    }
}