package com.thusee.footballevent.ui.teamsdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TeamMatchesDetailsScreen(
    teamId: String,
    teamName: String
) {
    val viewModel: DetailsViewModel = hiltViewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                bottom = 90.dp
            )
            .background(MaterialTheme.colorScheme.background)
    ) {

    }
}