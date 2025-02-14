package com.devfinity.composeboilerplate.base

import com.devfinity.composeboilerplate.routes.Screen

interface BaseUiState {
    var isLoading: Boolean?
    var errorMessage: String?
    var navigateTo: Screen?
}