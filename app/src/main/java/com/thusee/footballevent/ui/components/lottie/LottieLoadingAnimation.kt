package com.thusee.footballevent.ui.components.lottie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.thusee.footballevent.R

@Composable
fun LottieLoadingAnimation() {

    val isPlaying by remember {
        mutableStateOf(true)
    }
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loading)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever
    )
    Box(modifier = Modifier.fillMaxWidth()) {

    }
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 150.dp,
                horizontal = 20.dp
            ),
        contentScale = ContentScale.FillBounds
    )
}