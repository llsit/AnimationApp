package com.example.animationapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.animationapp.NavigationItem
import com.example.animationapp.ui.theme.AnimationAppTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = { navController.navigate(NavigationItem.ANIMATION.route) }) {
            Text(text = "Animate Page")
        }
        Button(onClick = { }) {
            Text(text = "Animate Shape")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    AnimationAppTheme {
        MainScreen()
    }
}