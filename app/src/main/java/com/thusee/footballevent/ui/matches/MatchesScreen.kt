package com.thusee.footballevent.ui.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.ui.matches.components.NestedScrollingView
import com.thusee.footballevent.ui.utils.UIState

@Composable
fun MatchesScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchesViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.value

    when (state) {
        is UIState.Loading -> {}
        is UIState.Success -> {
            MatchesList(state.data)
        }

        is UIState.Error -> {
            "errorMessage = ${state.exception.message}"
        }

        UIState.Empty -> {}
    }
}

@Composable
fun MatchesList(matches: Matches) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        NestedScrollingView(matches)
    }
}