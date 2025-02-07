package com.devfinity.composeboilerplate.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.devfinity.composeboilerplate.base.Navigator
import com.devfinity.composeboilerplate.routes.Screen

@Composable
fun DisposableLaunchEffectNavigator(
    value: Navigator?,
    onTriggerAction: (Screen) -> Unit
) {
    DisposableEffect(value) {
        value?.navigateTo?.let { onTriggerAction(it) }

        onDispose {
            value?.navigateTo = null
        }
    }
}
