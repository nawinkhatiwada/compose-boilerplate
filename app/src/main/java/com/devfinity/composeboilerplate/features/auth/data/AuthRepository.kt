package com.devfinity.composeboilerplate.features.auth.data

import com.devfinity.composeboilerplate.features.auth.data.model.LoginResponse

interface AuthRepository {
    suspend fun login(username: String): LoginResponse

    interface Local {
        suspend fun saveUserInfo(response: LoginResponse)
    }

    interface Remote {
        suspend fun login(username: String): LoginResponse
    }
}