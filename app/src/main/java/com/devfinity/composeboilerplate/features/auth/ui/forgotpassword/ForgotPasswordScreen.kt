package com.devfinity.composeboilerplate.features.auth.ui.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devfinity.composeboilerplate.utils.helper.NavigateBackWithRefresh

@Composable
fun ForgotPasswordScreen(
    uiState: ForgotPasswordUiState,
    navigateBackWithRefresh: NavigateBackWithRefresh
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {

        }
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            Text(
                text = "Forgot Password Screen"
            )
            Button(onClick = {
                navigateBackWithRefresh(true)
            }) {
                Text("Go to Login")
            }
        }
    }
}