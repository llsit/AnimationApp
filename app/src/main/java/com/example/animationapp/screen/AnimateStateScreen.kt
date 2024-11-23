package com.example.animationapp.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.animationapp.component.CustomToolbar

@Composable
fun AnimateStateScreen(navController: NavHostController = rememberNavController()) {

    var isClicked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomToolbar(
                title = "Animate State",
                onClickListener = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Column {
            val color by animateColorAsState(
                targetValue = if (isClicked) Color.Green else Color.Red, label = ""
            )

            Box(modifier = Modifier
                .padding(innerPadding)
                .size(100.dp)
                .background(color)
                .clickable { isClicked = !isClicked })
        }
    }
}