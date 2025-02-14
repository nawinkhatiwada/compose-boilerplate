package com.devfinity.composeboilerplate.features.auth.ui.login

import com.devfinity.composeboilerplate.base.BaseUiState
import com.devfinity.composeboilerplate.features.auth.data.model.LoginResponse
import com.devfinity.composeboilerplate.routes.Screen

data class LoginUiState(
    override var isLoading: Boolean? = null,
    override var errorMessage: String? = null,
    override var navigateTo: Screen? = null,
    val loginResponse: LoginResponse? = null
) : BaseUiState