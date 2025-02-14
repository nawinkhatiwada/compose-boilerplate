package com.devfinity.composeboilerplate.features.auth.data

import com.devfinity.composeboilerplate.features.auth.data.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val local: AuthRepository.Local,
    private val remote: AuthRepository.Remote
) : AuthRepository {
    override suspend fun login(username: String): LoginResponse {
        return withContext(Dispatchers.IO) {
            val res = remote.login(username)
            local.saveUserInfo(res)
            res
        }
    }
}