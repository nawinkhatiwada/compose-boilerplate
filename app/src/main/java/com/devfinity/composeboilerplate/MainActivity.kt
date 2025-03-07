package com.devfinity.composeboilerplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.devfinity.composeboilerplate.routes.MainNavGraph
import com.devfinity.composeboilerplate.routes.Screen
import com.devfinity.composeboilerplate.ui.styling.theme.ComposeBoilerplateTheme
import com.devfinity.composeboilerplate.utils.NavigationCommand
import com.devfinity.composeboilerplate.utils.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBoilerplateTheme {
                val navController = rememberNavController()
                /*MainNavGraph(
                    navController = navController,
                    startDestination = Screen.Login
                )*/
                LaunchedEffect(Unit) {
                    lifecycleScope.launch {
                        navigationManager.commands.collectLatest { command ->
                            when (command) {
                                is NavigationCommand.To -> navController.navigate(command.direction)
                                is NavigationCommand.Back -> navController.popBackStack()
                            }
                        }
                    }
                }

//                MainNavGraph(navController)
                MainNavGraph(
                    navController = navController,
                    startDestination = Screen.Login
                )
            }
        }
    }
}/*

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
    ComposeBoilerplateTheme {
        TextInputExample()
    }
}*/