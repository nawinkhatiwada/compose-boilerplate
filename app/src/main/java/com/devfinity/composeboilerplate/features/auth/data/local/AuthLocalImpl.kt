package com.devfinity.composeboilerplate.features.auth.data.local

import com.devfinity.composeboilerplate.features.auth.data.AuthRepository
import com.devfinity.composeboilerplate.features.auth.data.model.LoginResponse
import com.devfinity.composeboilerplate.persistance.prefs.local.SharedPrefManager
import javax.inject.Inject

class AuthLocalImpl @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) : AuthRepository.Local {
    override suspend fun saveUserInfo(response: LoginResponse) {

    }
}