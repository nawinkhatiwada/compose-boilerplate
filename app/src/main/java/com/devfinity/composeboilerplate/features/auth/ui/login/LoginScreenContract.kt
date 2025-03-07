package com.devfinity.composeboilerplate.features.auth.ui.login

import com.devfinity.composeboilerplate.routes.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LoginScreenContract {

    interface ViewModel {
        val viewState: StateFlow<ViewState>

        val notification: Flow<Screen>

        fun onEvent(event: Event)
    }

    sealed interface ViewState {
        data object Initial: ViewState

        data object Loading: ViewState

        data class Error(val message: String): ViewState
    }

    sealed interface Event {
        data object OnForgotPasswordClicked : Event

        data class OnLoginClicked(val username: String) : Event

        data object OnShowToastClicked : Event
    }
}
