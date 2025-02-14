package com.devfinity.composeboilerplate.utils.helper

typealias NavigateBack = () -> Unit
typealias NavigateBackWithRefresh = (ShouldRefresh) -> Unit
typealias ShouldRefresh = Boolean
typealias ToastMessage = String