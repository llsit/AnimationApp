package com.example.animationapp.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.animationapp.component.CustomToolbar

@Composable
fun TransitionScreen(navController: NavHostController = rememberNavController()) {
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    Scaffold(
        topBar = {
            CustomToolbar(
                title = "Transition",
                onClickListener = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)) {
                AnimatedVisibility(visibleState = state) {
                    Text(text = "Hello, world!")
                }

                // Use the MutableTransitionState to know the current animation state
                // of the AnimatedVisibility.
                Text(
                    text = when {
                        state.isIdle && state.currentState -> "Visible"
                        !state.isIdle && state.currentState -> "Disappearing"
                        state.isIdle && !state.currentState -> "Invisible"
                        else -> "Appearing"
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TransitionScreenPreview() {
    TransitionScreen()
}