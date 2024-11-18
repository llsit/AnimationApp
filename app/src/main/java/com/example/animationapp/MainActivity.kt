package com.example.animationapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animationapp.screen.AnimationScreen
import com.example.animationapp.screen.MainScreen
import com.example.animationapp.screen.TransitionScreen
import com.example.animationapp.ui.theme.AnimationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        setContent {
            val navController = rememberNavController()

            AnimationAppTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavigationItem.Home.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(NavigationItem.Home.route) {
                            MainScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable(NavigationItem.Animation.route) {
                            AnimationScreen(navController = navController)
                        }
                        composable(NavigationItem.Transition.route) {
                            TransitionScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class Screen {
    MAIN,
    ANIMATION,
    TRANSITION
}

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.MAIN.name)
    object Animation : NavigationItem(Screen.ANIMATION.name)
    object Transition : NavigationItem(Screen.TRANSITION.name)
}
