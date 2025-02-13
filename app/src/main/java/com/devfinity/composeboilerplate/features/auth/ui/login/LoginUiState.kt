package com.devfinity.composeboilerplate.features.auth.ui.login

import com.devfinity.composeboilerplate.base.Navigator
import com.devfinity.composeboilerplate.routes.Screen

data class LoginUiState(
    override var isLoading: Boolean? = null,
    override var data: Any? = null,
    override var hasError: Boolean? = null,
    override var errorMessage: String? = null,
    override var navigateTo: Screen? = null,
) : Navigator