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

    fun login() {
        viewModelScope.launch {
            startLoading()
            try {
                val response = repository.login()
                setSuccess(response)
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
                        navigateTo = Screen.ForgotPassword(id = event.id, title = event.title)
                    )
                )
            }
        }
    }
}

sealed class LoginUiEvent {
    data class OnForgotPasswordClicked(
        val id: Int, val title: String
    ) : LoginUiEvent()
}