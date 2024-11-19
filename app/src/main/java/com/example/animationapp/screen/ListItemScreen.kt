package com.example.animationapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.animationapp.component.CustomToolbar
import kotlinx.coroutines.delay

@Composable
fun ListItemScreen(navController: NavHostController) {
    val items = remember { mutableStateListOf<String>() }
    LaunchedEffect(Unit) {
        delay(2000)
        for (i in 1..10) {
            items.add(
                "Item $i"
            )
        }
    }

    Scaffold(
        topBar = {
            CustomToolbar(
                title = "List Item",
                onClickListener = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(items) { item ->
                Row(
                    Modifier
                        .animateItem()
                        .padding(8.dp)
                        .background(Color.Cyan),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(modifier = Modifier.padding(8.dp), text = item)
                }
            }
        }
    }

}