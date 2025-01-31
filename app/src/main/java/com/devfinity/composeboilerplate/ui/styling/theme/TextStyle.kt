package com.devfinity.composeboilerplate.ui.styling.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Immutable
data class AppTextStyle(
    val text_600_11_black: TextStyle,
)

val appTextStyle = AppTextStyle(
    text_600_11_black = TextStyle(
        fontWeight = FontWeight.SemiBold,
        color = black_000000,
        fontSize = textSize11
    )
)