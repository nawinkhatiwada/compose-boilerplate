package com.devfinity.composeboilerplate.features.auth.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("username") var username: String,
    @SerializedName("email") var email: String
)