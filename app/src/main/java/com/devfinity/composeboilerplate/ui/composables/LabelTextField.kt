package com.devfinity.composeboilerplate.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfinity.composeboilerplate.ui.styling.theme.spacing_8

@Composable
fun LabelTextField(
    modifier: Modifier = Modifier,
    inputText: String = "",
    textInputData: TextInputData = TextInputData(),
    iconData: TextInputFieldIconData? = null,
    keyboardOptions: KeyboardOptions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colorData: TextInputColorData? = null,
    styleData: TextInputFieldStyleData? = null,
    onTextChanged: (String) -> Unit = {},
    onImeDoneClicked: () -> Unit = {},
    onTextFieldClicked: () -> Unit = {},
) {
    val isEnabled = textInputData.isEnabled
    val clickable = textInputData.clickable
    val isRequired = textInputData.isRequired
    val errorText = textInputData.errorText
    val labelText = textInputData.labelText
    val shape = textInputData.shape
    val hintText = textInputData.hintText

    val focusedBorderColor = colorData?.focusedBorderColor ?: Color.Blue
    val errorBorderColor = Color.Red // Error border color
    val defaultBorderColor = Color.Gray // Default border color
    val disabledTextColor = colorData?.disabledTextColor ?: Color.Gray
    val disabledLabelColor = colorData?.disabledLabelColor ?: Color.Gray

    val textStyle = styleData?.textStyle ?: TextStyle.Default
    val labelTextStyle = styleData?.labelTextStyle ?: TextStyle.Default
    val errorTextStyle = styleData?.errorTextStyle ?: TextStyle.Default
    val hintTextStyle = styleData?.hintTextStyle ?: TextStyle.Default

    val keyboardActions = KeyboardActions(
        onDone = {
            onImeDoneClicked()
        }
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        if (labelText != null) {

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text(
                    text = labelText,
                    style = labelTextStyle,
                )
                Spacer(modifier = Modifier.width(4.dp))
                if(isRequired) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically).padding(top = 4.dp),
                        text = "*",
                        style = labelTextStyle,
                    )
                }
            }
        }
        val enableFocusBorderColor = textInputData.enableFocusBorderColor
        BasicTextField(
            modifier = Modifier
                .clickable(enabled = clickable) { onTextFieldClicked() }
                .focusable(isEnabled)
                .border(
                    1.dp,
                    if (enableFocusBorderColor) {
                        if (errorText.isNullOrEmpty()) {
                            if (isFocused.value) focusedBorderColor else defaultBorderColor
                        } else {
                            errorBorderColor
                        }
                    } else {
                        defaultBorderColor
                    },
                    shape
                )
                .padding(horizontal = 0.dp),
            value = inputText,
            onValueChange = { onTextChanged(it) },
            keyboardOptions = keyboardOptions ?: KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            enabled = isEnabled,
            textStyle = textStyle.copy(color = if (isEnabled) Color.Black else disabledTextColor),
            interactionSource = interactionSource,

            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth().height(40.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        iconData?.leadingIcon?.let {
                            IconButton(
                                onClick = { iconData.onLeadingIconClicked.invoke() }
                            ) {
                                Icon(
                                    painter = painterResource(id = it),
                                    contentDescription = "Leading Icon",
                                    tint = iconData.leadingIconTint ?: Color.Gray,
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
//                                .height(36.dp)
                                .padding(
                                    top = if (iconData?.leadingIcon != null) {
                                        0.dp
                                    } else {
                                        12.dp
                                    },
                                    bottom = if (iconData?.leadingIcon != null) {
                                        0.dp
                                    } else {
                                        12.dp
                                    },
                                    start = if (iconData?.leadingIcon != null) {
                                        0.dp
                                    } else {
                                        16.dp
                                    }
                                )
                        ) {
                            if (inputText.isEmpty()) {
                                Text(
                                    text = hintText.orEmpty(),
                                    style = hintTextStyle,
                                    textAlign = TextAlign.Center
                                )
                            }
                            innerTextField()
                        }

                        iconData?.trailingIcon?.let {
                            IconButton(
                                onClick = { iconData.onTrailingIconClicked.invoke() }
                            ) {
                                Icon(
                                    painter = painterResource(id = it),
                                    contentDescription = "Trailing Icon",
                                    tint = iconData.trailingIconTint ?: Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        )

        if (!errorText.isNullOrEmpty()) {
            Text(
                text = errorText,
                style = errorTextStyle,
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Immutable
data class TextInputData(
    val labelText: String? = null,
    val hintText: String? = null,
    val errorText: String? = null,
    val isRequired: Boolean = false,
    val isEnabled: Boolean = true,
    val shape: Shape = RoundedCornerShape(spacing_8),
    val minLines: Int = 1,
    val maxLines: Int = 1,
    val enableFocusBorderColor: Boolean = true,
    val clickable: Boolean = false,
)

@Immutable
data class TextInputColorData(
    val focusedBorderColor: Color,
    val disabledTextColor: Color,
    val disabledLabelColor: Color,
    val disabledHintColor: Color
)

@Immutable
data class TextInputFieldIconData(
    @DrawableRes val leadingIcon: Int? = null,
    val leadingIconTint: Color? = null,
    @DrawableRes val trailingIcon: Int? = null,
    val trailingIconTint: Color? = null,
    val onLeadingIconClicked: () -> Unit = {},
    val onTrailingIconClicked: () -> Unit = {}
)

@Immutable
data class TextInputFieldStyleData(
    val textStyle: TextStyle? = null,
    val labelTextStyle: TextStyle? = null,
    val errorTextStyle: TextStyle? = null,
    val hintTextStyle: TextStyle? = null
)

@Preview(showBackground = false)
@Composable
fun LabelTextFieldPreview() {
    LabelTextField(
        inputText = "Hello",
        textInputData = TextInputData(
            labelText = "Label",
            hintText = "Enter something...",
            errorText = "Error message",
            isRequired = true
        ),
        iconData = TextInputFieldIconData(
            leadingIcon = android.R.drawable.ic_menu_add,
            trailingIcon = android.R.drawable.ic_menu_search,
            leadingIconTint = null,
            onLeadingIconClicked = { /* Add action for leading icon click */ },
            onTrailingIconClicked = { /* Add action for trailing icon click */ }
        ),
        colorData = TextInputColorData(
            focusedBorderColor = Color.Blue,
            disabledTextColor = Color.Gray,
            disabledLabelColor = Color.Gray,
            disabledHintColor = Color.LightGray
        ),
        styleData = TextInputFieldStyleData(
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            labelTextStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
            errorTextStyle = TextStyle(fontSize = 12.sp, color = Color.Red),
            hintTextStyle = TextStyle(fontSize = 14.sp, color = Color.LightGray)
        ),
        onTextChanged = { newText -> /* Handle text change */ },
        onImeDoneClicked = { /* Handle IME Done */ },
        onTextFieldClicked = { /* Handle text field clicked */ },
    )
}
