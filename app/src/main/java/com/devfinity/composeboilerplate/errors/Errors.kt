package com.devfinity.composeboilerplate.errors

import android.app.Activity
import android.content.Context
import java.io.IOException

class FailedResponseException(val code: Int, val errorMessage: String) : IOException()
class NetworkNotAvailableException(val errorMessage: String) : IOException()

class UnauthorizedAccessException(val errorMessage: String) : Exception(errorMessage)

class ResponseErrorException(val errorMessage: String) : Exception(errorMessage)

class InvalidResponseException(val errorMessage: String) : Exception(errorMessage)

fun Activity.parseError(e: Exception): String {
    return parseException(e)
}

fun Context.parseError(e: Exception): String {
    return parseException(e)
}

private fun parseException(e: Exception): String {
    return when (e) {
        is FailedResponseException -> e.errorMessage
        is NetworkNotAvailableException -> e.errorMessage
        is UnauthorizedAccessException -> e.errorMessage
        is ResponseErrorException -> e.errorMessage
        is InvalidResponseException -> e.errorMessage
        else -> e.message ?: "Oops! something went wrong"
    }
}