package com.thusee.footballevent.ui.components.vertical

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.thusee.footballevent.domain.model.Match

@Composable
fun MatchVerticalList(matchList: List<Match>) {
        LazyColumn(content = {
            items(matchList) { match ->
                VerticalMatchCard(match = match)
            }
        })
}

@Preview
@Composable
fun HorizontalListPreview() {
    MatchVerticalList(
        matchList = listOf(
            Match("Team A", "2022-04-24T18:00:00.000Z", "Description 1", "Highlights 1", "Team B", "Team A"),
            Match("Team C", "2022-04-24T18:00:00.000Z", "Description 2", "Highlights 2", "Team D", "Team C")
        )
    )
}