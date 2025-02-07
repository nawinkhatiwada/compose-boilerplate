package com.devfinity.composeboilerplate.features.auth.ui.login

import com.devfinity.composeboilerplate.base.Navigator
import com.devfinity.composeboilerplate.features.auth.data.model.LoginResponse
import com.devfinity.composeboilerplate.routes.Screen

data class LoginUiState(
    val isLoading: Boolean? = false,
    val errorMessage: String? = null,
    val data: LoginResponse? = null,
    override var navigateTo: Screen? = null
) : Navigator