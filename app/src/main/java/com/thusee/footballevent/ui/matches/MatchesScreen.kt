package com.thusee.footballevent.ui.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.ui.components.ErrorScreen
import com.thusee.footballevent.ui.components.NestedScrollingView
import com.thusee.footballevent.ui.components.lottie.LottieLoadingAnimation
import com.thusee.footballevent.ui.common.UIState
import com.thusee.footballevent.ui.common.errors.state.ErrorDisplayInfo
import timber.log.Timber

@Composable
fun MatchesScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchesViewModel = hiltViewModel()
) {
    Box(modifier = modifier.fillMaxSize()) {

        when (val state = viewModel.uiState) {
            is UIState.Loading -> LottieLoadingAnimation()
            is UIState.Success -> {
                MatchesList(modifier = modifier, matches = state.data)
            }

            is UIState.Error<ErrorDisplayInfo> -> {
                val errorMessage = stringResource(id = state.errorData.messageResource)

                ErrorScreen(
                    errorMessage = errorMessage,
                    modifier = modifier
                )
                Timber.d("Error $errorMessage")
            }

            UIState.Empty -> {}
        }
    }
}

@Composable
fun MatchesList(
    modifier: Modifier = Modifier,
    matches: Matches
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                bottom = 90.dp
            )
            .background(MaterialTheme.colorScheme.background)
    ) {
        NestedScrollingView(modifier, matches)
    }
}