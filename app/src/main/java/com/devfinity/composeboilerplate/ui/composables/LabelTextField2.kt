package com.devfinity.composeboilerplate.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LabelTextField2(
    modifier: Modifier,
    label: @Composable ColumnScope.() -> Unit = { DefaultLabel() },
    textField: @Composable ColumnScope.() -> Unit = { DefaultTextField() },
    errorMessage: @Composable ColumnScope.() -> Unit = { DefaultErrorMessage() }
) {
    Column(modifier = modifier) {
        label()
        textField()
        errorMessage()
    }
}

@Composable
fun LabelTextField2(
    modifier: Modifier,
    label: String,
    textField: @Composable ColumnScope.() -> Unit = { DefaultTextField() },
    errorMessage: @Composable ColumnScope.() -> Unit = { DefaultErrorMessage() }
) {
    LabelTextField2(modifier = modifier, label = {
        DefaultLabel(text = label)
    }, textField = {
        textField()
    }, errorMessage = {
        errorMessage()
    })
}

@Composable
fun LabelTextField2(
    modifier: Modifier,
    label: String,
    errorMessage: String? = null,
    textField: @Composable ColumnScope.() -> Unit = { DefaultTextField() },
) {
    LabelTextField2(modifier = modifier, label = {
        DefaultLabel(text = label)
    }, textField = {
        textField()
    }, errorMessage = {
        errorMessage?.let {
            DefaultErrorMessage(text = errorMessage)
        }
    })
}

@Composable
fun DefaultLabel(
    modifier: Modifier = Modifier,
    text: String? = null,
    labelStyle: TextStyle = TextStyle.Default
) {
    text?.let {
        Text(modifier = modifier, text = text, style = labelStyle)
    }
}

@Composable
fun DefaultErrorMessage(
    modifier: Modifier = Modifier,
    text: String? = null,
    errorTextStyle: TextStyle = TextStyle.Default
) {
    text?.let {
        Text(modifier = modifier, text = text, style = errorTextStyle)
    }
}

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    inputText: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle = TextStyle.Default,
    hintText: String? = null,
    hintTextStyle: TextStyle = TextStyle.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    decorationBox: @Composable (Boolean, Boolean, @Composable () -> Unit) -> Unit = { isFocused, error, innerTextField ->
        DefaultBorder(
            modifier = Modifier,
            isFocused = isFocused,
            isError = error,
            innerTextField = innerTextField
        )
    },
    isError: Boolean = false,
    isEnabled: Boolean = true,
    onIconClicked: (() -> Unit)? = null,
    onTextFieldClicked: (() -> Unit)? = null,
    onTextChanged: (String) -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(inputText) }
    Column(modifier = modifier) {
        TextFieldContent(
            inputText = text,
            onTextChanged = { newText ->
                text = newText
                onTextChanged(newText)
            },
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            textStyle = textStyle,
            isEnabled = isEnabled,
            decorationBox = { innerTextField -> decorationBox(isFocused, isError, innerTextField) },
            onTextFieldClicked = onTextFieldClicked,
            onFocusChanged = { isFocused = it }
        )
        HintTextVisibility(hintText, text, hintTextStyle)
        IconRow(leadingIcon, trailingIcon, onIconClicked)
    }
}

@Composable
fun TextFieldContent(
    inputText: String,
    onTextChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    textStyle: TextStyle,
    isEnabled: Boolean,
    decorationBox: @Composable (@Composable () -> Unit) -> Unit,
    onTextFieldClicked: (() -> Unit)? = null,
    onFocusChanged: (Boolean) -> Unit
) {
    BasicTextField(value = inputText,
        onValueChange = { onTextChanged(it) },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        textStyle = textStyle,
        enabled = isEnabled,
        decorationBox = { innerTextField ->
            decorationBox(innerTextField)
        },
        modifier = Modifier
            .clickable { onTextFieldClicked?.invoke() }
            .onFocusChanged { onFocusChanged(it.isFocused) }
//            .padding(12.dp)
    )
}

@Composable
fun HintTextVisibility(hintText: String?, inputText: String, hintTextStyle: TextStyle) {
    if (hintText != null && inputText.isEmpty()) {
        DefaultHintText(text = hintText, hintTextStyle = hintTextStyle)
    }
}

@Composable
fun DefaultHintText(
    modifier: Modifier = Modifier,
    text: String? = null,
    hintTextStyle: TextStyle = TextStyle.Default
) {
    text?.let {
        Text(
            modifier = modifier,
            text = text,
            style = hintTextStyle
        )
    }
}

@Composable
fun IconRow(
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onIconClicked: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        leadingIcon?.let { IconButton(onClick = { onIconClicked?.invoke() }) { it() } }
        trailingIcon?.let { IconButton(onClick = { onIconClicked?.invoke() }) { it() } }
    }
}

@Composable
fun DefaultBorder(
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    isFocused: Boolean = false,
    innerTextField: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                width = 2.dp, color = when {
                    isError -> Color.Red
                    isFocused -> Color.Blue
                    else -> Color.Green
                }, shape = RoundedCornerShape(8.dp)
            ).padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        innerTextField()
    }
}
