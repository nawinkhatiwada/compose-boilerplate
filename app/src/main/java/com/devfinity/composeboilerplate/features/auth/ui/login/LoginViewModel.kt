package com.devfinity.composeboilerplate.features.auth.ui.login

import androidx.lifecycle.viewModelScope
import com.devfinity.composeboilerplate.base.BaseViewModel
import com.devfinity.composeboilerplate.features.auth.data.AuthRepository
import com.devfinity.composeboilerplate.routes.Screen
import com.devfinity.composeboilerplate.utils.helper.stringprovider.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val stringProvider: StringProvider
) : BaseViewModel<LoginUiState>(LoginUiState()) {

    private fun login(event: LoginUiEvent.OnLoginClicked) {
        viewModelScope.launch {
            startLoading()
            try {
                val response = repository.login(event.username)
                updateUiState(
                    uiState = getSuccessState().copy(
                        loginResponse = response
                    )
                )
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

    fun onTriggeredLoginEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnForgotPasswordClicked -> {
                updateUiState(
                    uiState = uiState.value.copy(
                        navigateTo = Screen.ForgotPassword
                    )
                )
            }

            is LoginUiEvent.OnLoginClicked -> login(event)
        }
    }
}

sealed class LoginUiEvent {
    data object OnForgotPasswordClicked : LoginUiEvent()

    data class OnLoginClicked(
        val username: String
    ) : LoginUiEvent()
}