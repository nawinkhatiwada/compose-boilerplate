package com.devfinity.composeboilerplate.remote.helper

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T
)