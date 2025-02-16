package com.devfinity.composeboilerplate.features.auth.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.devfinity.composeboilerplate.R
import com.devfinity.composeboilerplate.base.BaseViewModel
import com.devfinity.composeboilerplate.errors.parseException
import com.devfinity.composeboilerplate.features.auth.data.AuthRepository
import com.devfinity.composeboilerplate.routes.Screen
import com.devfinity.composeboilerplate.utils.helper.stringprovider.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navController: NavController,
    private val repository: AuthRepository, private val stringProvider: StringProvider
) : ViewModel(), LoginScreenContract.ViewModel {

    private val _viewState =
        MutableStateFlow<LoginScreenContract.ViewState>(LoginScreenContract.ViewState.Initial)
    override val viewState: StateFlow<LoginScreenContract.ViewState>
        get() = _viewState
    override val notification: Flow<String>
        get() = TODO("Not yet implemented")

    override val navigation: Flow<Screen>
        get() = MutableSharedFlow()

    override suspend fun onEvent(event: LoginScreenContract.Event) {
        TODO("Not yet implemented")
    }

    private fun startLoading() {
        _viewState.update { LoginScreenContract.ViewState.Loading }
    }

    private fun setError(e: Exception) {
        _viewState.update {
            LoginScreenContract.ViewState.Error(e.message ?: e.toString())
        }
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