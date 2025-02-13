package com.devfinity.composeboilerplate.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Login: Screen()

    @Serializable
    data class ForgotPassword(
        var id: Int,
        var title: String,
    ): Screen()
}

