package com.devfinity.composeboilerplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.devfinity.composeboilerplate.ui.components.LabelTextField
import com.devfinity.composeboilerplate.ui.components.TextInputColorData
import com.devfinity.composeboilerplate.ui.components.TextInputData
import com.devfinity.composeboilerplate.ui.components.TextInputFieldIconData
import com.devfinity.composeboilerplate.ui.components.TextInputFieldStyleData
import com.devfinity.composeboilerplate.ui.styling.theme.ComposeboilerplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeboilerplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextInputExample(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TextInputExample(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    val iconData = TextInputFieldIconData(
        leadingIcon = android.R.drawable.ic_menu_search,
        leadingIconTint = Color.Gray,
        trailingIcon = android.R.drawable.ic_notification_clear_all,
        trailingIconTint = Color.Gray,
        onLeadingIconClicked = { },
        onTrailingIconClicked = {
            text = ""
        }
    )

    val colorData = TextInputColorData(
        focusedBorderColor = Color.Blue,
        disabledTextColor = Color.Gray,
        disabledLabelColor = Color.LightGray,
        disabledHintColor = Color.LightGray,
    )

    val textInputData = TextInputData(
        labelText = "Search Data",
        hintText = "Search...",
        errorText = errorText,
        isRequired = false,
        isEnabled = true,
        clickable = false,
        enableFocusBorderColor = true
    )

    val styleData = TextInputFieldStyleData(
        textStyle = TextStyle(fontSize = 14.sp),
        labelTextStyle = TextStyle(
            fontSize = 14.sp,
            color = Color.Black
        ),
        errorTextStyle = TextStyle(
            fontSize = 12.sp,
            color = Color.Red
        ),
        hintTextStyle = TextStyle(
            fontSize = 14.sp,
            color = Color.Gray
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LabelTextField(
            modifier = modifier.fillMaxWidth(),
            inputText = text,
            textInputData = textInputData,
            iconData = iconData,
            colorData = colorData,
            styleData = styleData,
            onTextChanged = { newText ->
                text = newText
                errorText = if (newText.isBlank()) "This field is required" else ""
            },
            onImeDoneClicked = {
                println("IME Done clicked")
            },
            onTextFieldClicked = {
                println("Text field clicked")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeboilerplateTheme {
        TextInputExample()
    }
}