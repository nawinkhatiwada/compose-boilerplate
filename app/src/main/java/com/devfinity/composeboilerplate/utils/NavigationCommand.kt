package com.devfinity.composeboilerplate.utils

import com.devfinity.composeboilerplate.routes.Screen

sealed class NavigationCommand {
    data class To(val direction: Screen) : NavigationCommand()
    data object Back : NavigationCommand()
}