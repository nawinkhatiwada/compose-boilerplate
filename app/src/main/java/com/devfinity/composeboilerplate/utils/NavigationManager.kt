package com.devfinity.composeboilerplate.utils

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Singleton

@Singleton
class NavigationManager {
    private val _commands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 1)
    val commands = _commands.asSharedFlow()

    fun navigate(destination: NavigationCommand) {
        _commands.tryEmit(destination)
    }
}

