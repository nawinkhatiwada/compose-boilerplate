package com.devfinity.composeboilerplate.features.auth.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.devfinity.composeboilerplate.ui.composables.AppToolbar
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun LoginScreen(
    uiState: LoginScreenContract.ViewState,
    notification: SharedFlow<String>, // Accept SharedFlow
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

    // Observe notification flow for toast messages

    // Show the toast if notification is not empty
    LaunchedEffect(Unit) {
        notification.collect { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    when(uiState) {
        is LoginScreenContract.ViewState.Initial -> {

        }
        is LoginScreenContract.ViewState.Error -> {}
        LoginScreenContract.ViewState.Loading -> {}
      /*  is LoginScreenContract.ViewState.ToastMessage ->  {
            LaunchedEffect(Unit) {
                if (uiState.message.isNotEmpty()) {
                }
            }
        }*/
    }

//    LaunchedEffect(notification) {
//        if (notification.isNotEmpty()) {
//            Toast.makeText(context, notification, Toast.LENGTH_SHORT).show()
//        }
//    }


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