package com.devfinity.composeboilerplate.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<UiState : BaseUiState>(initialState: UiState) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> get() = _uiState

    protected fun updateUiState(uiState: UiState): UiState {
        _uiState.value = uiState
        return _uiState.value
    }
}