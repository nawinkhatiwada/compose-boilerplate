package com.devfinity.composeboilerplate.base

import com.devfinity.composeboilerplate.routes.Screen
import com.devfinity.composeboilerplate.utils.helper.ToastMessage

interface BaseUiState {
    var isLoading: Boolean?
    var toastMessage: ToastMessage?
    var errorMessage: String?
    var navigateTo: Screen?
}