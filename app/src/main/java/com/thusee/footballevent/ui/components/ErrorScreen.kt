package com.thusee.footballevent.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreen(
    errorMessage: String,
    modifier: Modifier = Modifier,
) {
    ErrorComponent(
        message = errorMessage,
        modifier = modifier,
    )
}