package com.thusee.footballevent.ui.teamsdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.ui.utils.UIState

@Composable
fun TeamMatchesDetailsScreen(
    modifier: Modifier = Modifier,
    teamId: String,
    teamName: String
) {
    val viewModel: DetailsViewModel = hiltViewModel()

    Box(modifier = modifier.fillMaxSize()) {

        when (val state = viewModel.uiState.value) {
            is UIState.Loading -> {}
            is UIState.Success -> {
                TeamMatchScheduleTabScreen(
                    matches = state.data,
                    teamName = teamName
                )
            }

            is UIState.Error -> {
                "errorMessage = ${state.exception.message}"
            }

            UIState.Empty -> {}
        }
    }
}

@Composable
fun TeamMatchScheduleTabScreen(
    modifier: Modifier = Modifier,
    matches: Matches,
    teamName: String
) {
    Box(modifier = modifier.fillMaxSize()) {
        TabScreen(matches, teamName)
    }
}
