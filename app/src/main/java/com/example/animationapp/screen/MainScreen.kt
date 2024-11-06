package com.example.animationapp.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animationapp.ui.theme.AnimationAppTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var visible by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top
    ) {
        HelloText()
        Button(onClick = {
            visible = !visible
        }) {
            Text("Click")
        }

        if (visible)
            Greeting(visible)
    }
}

@Composable
fun Greeting(visible: Boolean, modifier: Modifier = Modifier) {
    val animatedAlpha by animateFloatAsState(
        targetValue = if (visible) 2.0f else 0f,
        label = "alpha"
    )
    AnimatedVisibility(visible) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Cyan)
                .graphicsLayer {
                    alpha = animatedAlpha
                },
            contentAlignment = Alignment.Center
        ) {
        }
    }
}

@Composable
fun HelloText(){
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF60DDAD),
        targetValue = Color(0xFF4285F4),
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "color"
    )

    BasicText(
        modifier = Modifier.padding(16.dp),
        text = "Hello Compose",
        color = {
            animatedColor
        },
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimationAppTheme {
        MainScreen()
    }
}