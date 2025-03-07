package com.devfinity.composeboilerplate.features.auth.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.devfinity.composeboilerplate.ui.composables.AppToolbar

@Composable
fun LoginScreen(
    uiState: LoginScreenContract.ViewState,
    onTriggeredLoginEvent: (LoginScreenContract.Event) -> Unit
) {
    val context = LocalContext.current
//    LaunchNavigation(uiState) { navigateTo ->
//        onTriggerNavigationTo(navigateTo)
//    }
    /*
    LaunchToastMessage(uiState) { toastMessage ->
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }

    LaunchErrorMessage(uiState) { errorMessage ->
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }*/

    Scaffold(topBar = {
        AppToolbar(
            trailingContent = {
                Text(text = "Trailing")
            }
        ) {

        }
    }) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            Text(
                text = "Login Screen"
            )
            Button(onClick = {
                onTriggeredLoginEvent(LoginScreenContract.Event.OnForgotPasswordClicked)
            }) {
                Text("Go to Forgot Password")
            }

            Button(onClick = {
                onTriggeredLoginEvent(
                    LoginScreenContract.Event.OnLoginClicked(username = "n.k")
                )
            }) {
                Text("Do Login")
            }

            Button(onClick = {
                onTriggeredLoginEvent(
                    LoginScreenContract.Event.OnShowToastClicked
                )
            }) {
                Text("Show Toast")
            }

        }
    }
}