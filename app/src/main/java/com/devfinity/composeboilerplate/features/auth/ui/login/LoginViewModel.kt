package com.devfinity.composeboilerplate.features.auth.ui.login

import androidx.lifecycle.ViewModel
import com.devfinity.composeboilerplate.features.auth.data.AuthRepository
import com.devfinity.composeboilerplate.routes.Screen
import com.devfinity.composeboilerplate.utils.NavigationCommand
import com.devfinity.composeboilerplate.utils.NavigationManager
import com.devfinity.composeboilerplate.utils.helper.stringprovider.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val repository: AuthRepository,
    private val stringProvider: StringProvider
) : ViewModel(), LoginScreenContract.ViewModel {

    private val _viewState =
        MutableStateFlow<LoginScreenContract.ViewState>(LoginScreenContract.ViewState.Initial)
    override val viewState: StateFlow<LoginScreenContract.ViewState>
        get() = _viewState
    override val notification: Flow<Screen>
        get() = TODO("Not yet implemented")

    override fun onEvent(event: LoginScreenContract.Event) {
        when (event) {
            LoginScreenContract.Event.OnForgotPasswordClicked -> {
                navigationManager.navigate(NavigationCommand.To(Screen.ForgotPassword))
            }

            is LoginScreenContract.Event.OnLoginClicked -> {

            }

            LoginScreenContract.Event.OnShowToastClicked -> {

            }
        }
    }

    private fun startLoading() {
        _viewState.update { LoginScreenContract.ViewState.Loading }
    }

    private fun setError(e: Exception) {
        _viewState.update {
            LoginScreenContract.ViewState.Error(e.message ?: e.toString())
        }
    }
}

sealed class LoginUiEvent {
    data object OnForgotPasswordClicked : LoginUiEvent()

    data class OnLoginClicked(
        val username: String
    ) : LoginUiEvent()

    data object OnShowToastClicked : LoginUiEvent()
}