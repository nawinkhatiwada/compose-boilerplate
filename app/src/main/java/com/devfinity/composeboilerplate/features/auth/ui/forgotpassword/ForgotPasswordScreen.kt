package com.devfinity.composeboilerplate.features.auth.ui.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ForgotPasswordScreen() {
    Column {
        Text(
            text = "Forgot Password Screen"
        )
        Button(onClick = {
        }) {
            Text("Go to Login")
        }
    }
}