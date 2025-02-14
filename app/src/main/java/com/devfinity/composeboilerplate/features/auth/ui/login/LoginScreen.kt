package com.devfinity.composeboilerplate.features.auth.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.devfinity.composeboilerplate.routes.Screen
import com.devfinity.composeboilerplate.ui.styling.theme.VerticalSpacer40
import com.devfinity.composeboilerplate.utils.DisposableLaunchEffectNavigator

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onTriggeredLoginEvent: (LoginUiEvent) -> Unit,
    onTriggerNavigationTo: (Screen) -> Unit
) {

    DisposableLaunchEffectNavigator(uiState) { navigateTo ->
        onTriggerNavigationTo(navigateTo)
    }

    Column {
        VerticalSpacer40()
        Text(
            text = "Login Screen"
        )
        Button(onClick = {
            onTriggeredLoginEvent(LoginUiEvent.OnForgotPasswordClicked)
        }) {
            Text("Go to Forgot Password")
        }

        Button(onClick = {
            onTriggeredLoginEvent(
                LoginUiEvent.OnLoginClicked(
                    username = "n.k"
                )
            )
        }) {
            Text("Do Login")
        }
    }
}