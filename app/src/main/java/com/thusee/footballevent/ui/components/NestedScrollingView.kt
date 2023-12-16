package com.thusee.footballevent.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Match
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.ui.components.horizontal.HorizontalMatchList
import com.thusee.footballevent.ui.components.vertical.VerticalMatchCard

@Composable
fun NestedScrollingView(
    modifier: Modifier = Modifier,
    matches: Matches,
    isFromDetails: Boolean = false
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                text = stringResource(R.string.previous_match),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            HorizontalMatchList(matches.previous, isFromDetails)

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.upcoming_match),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(matches.upcoming) {
            VerticalMatchCard(match = it)
        }
    }
}

@Preview
@Composable
fun NestedScrollingViewPreview() {
    NestedScrollingView(
        modifier = Modifier,
        Matches(
            previous = listOf(
                Match("Team A", "2022-04-24T18:00:00.000Z", "Description 1", "Highlights 1", "Team B", "Team A"),
                Match("Team C", "2022-04-24T18:00:00.000Z", "Description 2", "Highlights 2", "Team D", "Team C")
            ),
            upcoming = listOf(
                Match("Team E", "2022-04-24T18:00:00.000Z", "Description 3", "Highlights 3", "Team F", "Team E")
            )
        )
    )
}