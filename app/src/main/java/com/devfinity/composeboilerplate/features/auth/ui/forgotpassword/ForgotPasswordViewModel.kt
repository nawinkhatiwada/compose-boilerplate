package com.devfinity.composeboilerplate.features.auth.ui.forgotpassword

import com.devfinity.composeboilerplate.base.BaseViewModel
import com.devfinity.composeboilerplate.features.auth.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel<ForgotPasswordUiState>(ForgotPasswordUiState()) {
    private fun startLoading() {
    }

    private fun setError(e: Exception) {
    }

    private fun getSuccessState(): ForgotPasswordUiState {
        val updatedUiState = updateUiState(
            uiState = uiState.value.copy(
                isLoading = false,
                errorMessage = null,
            )
        )
        return updatedUiState
    }
}