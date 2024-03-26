package com.thusee.footballevent.ui.teamsdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.ui.components.ErrorScreen
import com.thusee.footballevent.ui.components.lottie.LottieLoadingAnimation
import com.thusee.footballevent.ui.common.UIState
import com.thusee.footballevent.ui.common.errors.state.ErrorDisplayInfo

@Composable
fun TeamMatchesDetailsScreen(
    modifier: Modifier = Modifier,
    teamName: String,
    navController: NavController
) {
    val viewModel: DetailsViewModel = hiltViewModel()

    Box(modifier = modifier.fillMaxSize()) {
        when (val state = viewModel.uiState) {
            is UIState.Loading -> LottieLoadingAnimation()
            is UIState.Success -> {
                TeamMatchScheduleTabScreen(
                    matches = state.data,
                    teamName = teamName,
                    navController = navController
                )
            }

            is UIState.Error<ErrorDisplayInfo> -> {
                ErrorScreen(
                    errorMessage = stringResource(id = state.errorData.messageResource),
                    modifier = modifier
                )
            }

            UIState.Empty -> {}
        }
    }
}

@Composable
fun TeamMatchScheduleTabScreen(
    modifier: Modifier = Modifier,
    matches: Matches,
    teamName: String,
    navController: NavController
) {
    Box(modifier = modifier.fillMaxSize()) {
        TabScreen(matches, teamName) {
            navController.popBackStack()
        }
    }
}
