package com.devfinity.composeboilerplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.devfinity.composeboilerplate.routes.MainNavGraph
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

                MainNavGraph(navController)
            }
        }
    }
}