package com.devfinity.composeboilerplate.base

import androidx.lifecycle.ViewModel
import com.devfinity.composeboilerplate.errors.parseException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<UiState : BaseUiState>(initialState: UiState) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> get() = _uiState

    protected fun updateUiState(uiState: UiState) {
        _uiState.value = uiState
    }

    protected fun startLoading() {
        _uiState.value.isLoading = true
        _uiState.value.errorMessage = null
    }

    protected fun getSuccessState(): UiState {
        _uiState.value.isLoading = false
        _uiState.value.errorMessage = null
        return _uiState.value
    }

    protected fun setError(e: Exception) {
        e.printStackTrace()
        _uiState.value.isLoading = false
        _uiState.value.errorMessage = parseException(e)
    }
}