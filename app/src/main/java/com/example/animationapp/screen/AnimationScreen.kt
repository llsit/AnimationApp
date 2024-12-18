package com.example.animationapp.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.animationapp.R
import com.example.animationapp.component.CustomToolbar
import com.example.animationapp.component.LoadingScreen
import com.example.animationapp.ui.theme.AnimationAppTheme
import kotlinx.coroutines.delay

@Composable
fun AnimationScreen(
    navController: NavHostController = rememberNavController()
) {
    var visible by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            CustomToolbar(
                title = "Animation",
                onClickListener = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Top
            ) {
                HelloRotation()
                HelloText()

                Button(onClick = {
                    visible = !visible
                }) {
                    Text("Click")
                }

                ItemBox(visible)

                LoadingContent()
                Spacer(Modifier.height(8.dp))
                InfiiteTransition()
            }
        }
    }
}

@Composable
fun ItemBox(visible: Boolean) {
    val animatedAlpha by animateFloatAsState(
        targetValue = if (visible) 1.0f else 0f,
        label = "alpha"
    )
    val density = LocalDensity.current
    val enter = slideInVertically {
        // Slide in from 40 dp from the top.
        with(density) { -40.dp.roundToPx() }
    } + expandVertically(
        // Expand from the top.
        expandFrom = Alignment.Top
    ) + fadeIn(
        // Fade in with the initial alpha of 0.3f.
        initialAlpha = 0.3f
    )
    val exit = slideOutVertically() + shrinkVertically() + fadeOut()
    val exitSlideOut = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(durationMillis = 300)
    ) + shrinkVertically(
        animationSpec = tween(delayMillis = 300)
    )
    var expanded by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = expanded, label = "")

    val size by transition.animateDp(label = "") { state ->
        if (state) 400.dp else 200.dp
    }

    AnimatedVisibility(
        visible,
        enter = enter,
        exit = exitSlideOut
    ) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(size)
                .animateContentSize()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Cyan)
                .graphicsLayer {
                    alpha = animatedAlpha
                }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    expanded = !expanded
                },
            contentAlignment = Alignment.Center
        ) {
        }
    }
}

@Composable
fun HelloRotation() {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Hello",
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
                .align(Alignment.Center),
            // Text composable does not take TextMotion as a parameter.
            // Provide it via style argument but make sure that we are copying from current theme
            style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
        )
    }
}

@Composable
fun HelloText() {
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

@Composable
fun InfiiteTransition() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(animation = tween(1000)), label = ""
    )

    Box(modifier = Modifier
        .size(100.dp)
        .background(color))
}

@Composable
fun LoadingContent() {
    var state by remember {
        mutableStateOf(UiState.Loading)
    }
    LaunchedEffect(Unit) {
        delay(5000)
        state = UiState.Loaded
    }
    AnimatedContent(
        state,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(2000)
            ) togetherWith fadeOut(animationSpec = tween(3000))
        },
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            state = when (state) {
                UiState.Loading -> UiState.Loaded
                UiState.Loaded -> UiState.Error
                UiState.Error -> UiState.Loading
            }
        },
        label = "Animated Content"
    ) { targetState ->
        when (targetState) {
            UiState.Loading -> {
                LoadingScreen()
            }

            UiState.Loaded -> {
                LoadedScreen()
            }

            UiState.Error -> {
                ErrorScreen()
            }
        }
    }
}

enum class UiState {
    Loading,
    Loaded,
    Error
}

@Composable
private fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // [START_EXCLUDE]
        Text("Error", fontSize = 18.sp)
        // [END_EXCLUDE]
    }
}

@Composable
private fun LoadedScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // [START_EXCLUDE]
        Text("Loaded", fontSize = 18.sp)
        Image(
            painterResource(id = R.drawable.dog),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            contentDescription = "dog",
            contentScale = ContentScale.Crop
        )
        // [END_EXCLUDE]
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationPreview() {
    AnimationAppTheme {
        AnimationScreen()
    }
}