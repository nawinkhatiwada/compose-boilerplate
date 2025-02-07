package com.devfinity.composeboilerplate.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.devfinity.composeboilerplate.features.auth.ui.forgotpassword.ForgotPasswordScreen
import com.devfinity.composeboilerplate.features.auth.ui.forgotpassword.ForgotPasswordViewModel
import com.devfinity.composeboilerplate.features.auth.ui.login.LoginScreen
import com.devfinity.composeboilerplate.features.auth.ui.login.LoginViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    startDestination: Screen = Screen.Login
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Login> {
            val viewModel: LoginViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LoginScreen(
                uiState = uiState,
                onTriggeredLoginEvent = { event ->
                    viewModel.onTriggeredLoginEvent(event)
                },
                onTriggerNavigationTo = { screen ->
                    navController.navigate(screen)
                }
            )
        }

        composable<Screen.ForgotPassword> {
            val args = it.toRoute<Screen.ForgotPassword>()
            val viewModel: ForgotPasswordViewModel = hiltViewModel()
            /*viewModel.uiState.value.copy(

            )*/
            ForgotPasswordScreen()
        }
    }
}