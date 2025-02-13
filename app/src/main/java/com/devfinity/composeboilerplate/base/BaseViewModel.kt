package com.devfinity.composeboilerplate.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<UiState : Navigator>(initialState: UiState) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> get() = _uiState

    protected fun updateUiState(uiState: UiState) {
        _uiState.value = uiState
    }

    protected fun startLoading() {
        _uiState.value.isLoading = true
        _uiState.value.errorMessage = null
        _uiState.value.data = null
    }

    protected fun setSuccess(data: Any) {
        _uiState.value.isLoading = false
        _uiState.value.errorMessage = null
        _uiState.value.data = data
    }

    protected fun setError(e: Exception) {
        e.printStackTrace()
        _uiState.value.isLoading = false
        _uiState.value.errorMessage = e.localizedMessage
        _uiState.value.data = null
    }
}