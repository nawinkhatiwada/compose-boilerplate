package com.devfinity.composeboilerplate.features.auth.ui.forgotpassword

import com.devfinity.composeboilerplate.base.BaseUiState
import com.devfinity.composeboilerplate.routes.Screen

data class ForgotPasswordUiState(
    var id: Int? = null,
    var title: String? = null,
    override var isLoading: Boolean? = null,
    override var errorMessage: String? = null,
    override var navigateTo: Screen? = null,
) : BaseUiState