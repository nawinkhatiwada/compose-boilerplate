package com.devfinity.composeboilerplate.features.auth.ui.forgotpassword

import com.devfinity.composeboilerplate.base.Navigator
import com.devfinity.composeboilerplate.routes.Screen

data class ForgotPasswordUiState(
    var id: Int? = null,
    var title: String? = null,
    override var navigateTo: Screen? = null
) : Navigator