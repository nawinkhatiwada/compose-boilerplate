package com.devfinity.composeboilerplate.remote.helper

import com.devfinity.composeboilerplate.errors.FailedResponseException

inline fun <reified T> notNullMapper(baseResponse: BaseResponse<T>): T {
    baseResponse.data?.let {
        return it
    }
    throw FailedResponseException(500, "Server Error!")
}
