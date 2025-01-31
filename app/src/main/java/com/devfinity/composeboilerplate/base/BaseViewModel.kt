package com.devfinity.composeboilerplate.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<UiState>(initialState: UiState) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> get() = _uiState

    protected fun updateUiState(uiState: UiState) {
        _uiState.value = uiState
    }
}