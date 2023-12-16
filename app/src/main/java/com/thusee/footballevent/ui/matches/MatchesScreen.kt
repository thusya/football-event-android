package com.thusee.footballevent.ui.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.ui.components.ErrorScreen
import com.thusee.footballevent.ui.components.NestedScrollingView
import com.thusee.footballevent.ui.components.lottie.LottieLoadingAnimation
import com.thusee.footballevent.ui.utils.UIState

@Composable
fun MatchesScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchesViewModel = hiltViewModel()
) {
    Box(modifier = modifier.fillMaxSize()) {
        val state = viewModel.uiState.value

        when (state) {
            is UIState.Loading -> LottieLoadingAnimation()
            is UIState.Success -> {
                MatchesList(state.data)
            }

            is UIState.Error -> {
                ErrorScreen(
                    exception = state.exception,
                    modifier = modifier
                )
                "errorMessage = ${state.exception.message}"
            }

            UIState.Empty -> {}
        }
    }
}

@Composable
fun MatchesList(matches: Matches) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                bottom = 90.dp
            )
            .background(MaterialTheme.colorScheme.background)
    ) {
        NestedScrollingView(matches)
    }
}