package com.devfinity.composeboilerplate.features.auth.ui.login

import com.devfinity.composeboilerplate.features.auth.data.model.LoginResponse

data class LoginUiState(
    val isLoading: Boolean? = false,
    val errorMessage: String? = null,
    val data: LoginResponse?= null
)