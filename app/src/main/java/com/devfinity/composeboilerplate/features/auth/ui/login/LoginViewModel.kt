package com.devfinity.composeboilerplate.features.auth.ui.login

import androidx.lifecycle.viewModelScope
import com.devfinity.composeboilerplate.base.BaseViewModel
import com.devfinity.composeboilerplate.features.auth.data.AuthRepository
import com.devfinity.composeboilerplate.utils.stringprovider.StringProvider
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
            updateUiState(
                uiState = uiState.value.copy(
                    isLoading = true,
                    errorMessage = null,
                    data = null
                )
            )
            try {
                val response = repository.login()
                updateUiState(
                    uiState = uiState.value.copy(
                        isLoading = false,
                        data = response
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                updateUiState(
                    uiState = uiState.value.copy(
                        isLoading = false,
                        errorMessage = e.message,
                        data = null
                    )
                )
            }
        }
    }
}