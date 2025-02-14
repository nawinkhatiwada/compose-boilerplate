package com.devfinity.composeboilerplate.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.devfinity.composeboilerplate.base.BaseUiState
import com.devfinity.composeboilerplate.routes.Screen
import com.devfinity.composeboilerplate.utils.helper.ToastMessage

@Composable
fun LaunchNavigation(
    value: BaseUiState?,
    onTriggerAction: (Screen) -> Unit
) {
    LaunchedEffect(value) {
        value?.navigateTo?.let {
            onTriggerAction(it)
            value.navigateTo = null
        }
    }
}

@Composable
fun LaunchToastMessage(
    value: BaseUiState?,
    onTriggerAction: (ToastMessage) -> Unit
) {
    LaunchedEffect(value) {
        value?.toastMessage?.let {
            onTriggerAction(it)
            value.toastMessage = null
        }
    }
}

@Composable
fun LaunchErrorMessage(
    value: BaseUiState?,
    onTriggerAction: (String) -> Unit
) {
    LaunchedEffect(value) {
        value?.errorMessage?.let {
            onTriggerAction(it)
            value.errorMessage = null
        }
    }
}
