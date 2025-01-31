package com.devfinity.composeboilerplate.features.auth.data.remote

import com.devfinity.composeboilerplate.features.auth.data.AuthRepository
import com.devfinity.composeboilerplate.features.auth.data.model.LoginResponse
import com.devfinity.composeboilerplate.remote.ApiService
import com.devfinity.composeboilerplate.remote.helper.notNullMapper
import javax.inject.Inject

class AuthRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository.Remote {
    override suspend fun login(): LoginResponse {
        val requestParams = mapOf(
            Pair("username", "nkhatiwada"),
            Pair("password", "mypass")
        )
        val baseResponse = apiService.login(requestParams)
        return notNullMapper(baseResponse)
    }
}