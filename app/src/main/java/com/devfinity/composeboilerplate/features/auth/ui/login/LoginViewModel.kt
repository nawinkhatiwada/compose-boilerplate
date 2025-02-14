package com.devfinity.composeboilerplate.features.auth.ui.login

import androidx.lifecycle.viewModelScope
import com.devfinity.composeboilerplate.R
import com.devfinity.composeboilerplate.base.BaseViewModel
import com.devfinity.composeboilerplate.errors.parseException
import com.devfinity.composeboilerplate.features.auth.data.AuthRepository
import com.devfinity.composeboilerplate.routes.Screen
import com.devfinity.composeboilerplate.utils.helper.stringprovider.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository, private val stringProvider: StringProvider
) : BaseViewModel<LoginUiState>(LoginUiState()) {

    private fun startLoading() {
        updateUiState(
            uiState = uiState.value.copy(
                isLoading = true, errorMessage = null, loginResponse = null
            )
        )
    }

    private fun setError(e: Exception) {
        updateUiState(
            uiState = uiState.value.copy(
                isLoading = false, errorMessage = parseException(e), loginResponse = null
            )
        )
    }

    private fun getSuccessState(): LoginUiState {
        val uiState = updateUiState(
            uiState = uiState.value.copy(
                isLoading = false, errorMessage = null
            )
        )
        return uiState
    }

    private fun doLogin(event: LoginUiEvent.OnLoginClicked) {
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

            is LoginUiEvent.OnLoginClicked -> {
                doLogin(event)
            }

            LoginUiEvent.OnShowToastClicked -> {
                updateUiState(
                    uiState = uiState.value.copy(
                        toastMessage = stringProvider.getString(R.string.test_toast_message)
                    )
                )
            }
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