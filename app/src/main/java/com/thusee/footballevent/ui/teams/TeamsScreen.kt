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
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.ui.utils.UIState
import timber.log.Timber

@Composable
fun TeamsScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamsViewModel = hiltViewModel()
) {

    val teamsState = viewModel.teamsState.collectAsState()

    when (val state = teamsState.value) {
        is UIState.Loading -> {}
        is UIState.Success -> TeamListScreen(state.data, modifier)
        is UIState.Error -> {
            Timber.d("Error ${state.exception.message}")
        }
        is UIState.Empty -> {}
    }
}

@Composable
fun TeamListScreen(
    list: List<Team>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
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
                TeamCardContainer(team = it, onTeamClick = {})
            }
        }
    }
}