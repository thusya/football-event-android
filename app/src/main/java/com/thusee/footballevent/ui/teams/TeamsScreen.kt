package com.thusee.footballevent.ui.teams

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.ui.components.ErrorScreen
import com.thusee.footballevent.ui.components.lottie.LottieLoadingAnimation
import com.thusee.footballevent.ui.navigation.DetailsScreen
import com.thusee.footballevent.ui.navigation.TEAM_ID
import com.thusee.footballevent.ui.navigation.TEAM_NAME
import com.thusee.footballevent.ui.utils.UIState
import timber.log.Timber

@Composable
fun TeamsScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamsViewModel = hiltViewModel(),
    navController: NavController
) {
    val teamsState = viewModel.teamsState.collectAsState()

    when (val state = teamsState.value) {
        is UIState.Loading -> LottieLoadingAnimation()
        is UIState.Success -> TeamListScreen(
            list = state.data,
            modifier = modifier,
            navController = navController
        )

        is UIState.Error -> {
            ErrorScreen(
                exception = state.exception,
                modifier = modifier
            )
            Timber.d("Error ${state.exception.message}")
        }

        is UIState.Empty -> {}
    }
}

@Composable
fun TeamListScreen(
    list: List<Team>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                bottom = 90.dp
            )
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(1),
            modifier = Modifier
                .fillMaxSize()
                .semantics { contentDescription = "" },
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        ) {
            items(list) {
                TeamCardContainer(
                    team = it,
                    onTeamClick = {
                        navController.navigate(
                            route = DetailsScreen.TeamMatchesDetails.route
                                .replace(
                                    oldValue = "{$TEAM_ID}",
                                    newValue = it.id
                                )
                                .replace(
                                    oldValue = "{$TEAM_NAME}",
                                    newValue = it.name
                                )
                        )
                    }
                )
            }
        }
    }
}