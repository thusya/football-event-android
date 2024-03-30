package com.thusee.footballevent.ui.teamsdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Match
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.ui.components.CustomToolbar
import com.thusee.footballevent.ui.components.horizontal.HorizontalMatchCard
import com.thusee.footballevent.ui.components.vertical.MatchVerticalList
import com.thusee.footballevent.ui.theme.poppins

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(
    matches: Matches,
    teamName: String,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {}
) {
    val tabItem = listOf(
        MatchTabItem(title = stringResource(id = R.string.finished)),
        MatchTabItem(title = stringResource(id = R.string.upcoming))
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        tabItem.size
    }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.scrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CustomToolbar(
            modifier = Modifier.fillMaxWidth(),
            title = teamName,
            showBackButton = true,
            onBack = onBack
        )
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItem.forEachIndexed { index, matchTabItem ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(text = matchTabItem.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = poppins,
                                fontWeight = FontWeight.Normal,
                            ))
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (index == 0) {
                    LazyColumn(content = {
                        items(matches.previous) { match ->
                            HorizontalMatchCard(match = match)
                        }
                    })
                } else {
                    MatchVerticalList(matches.upcoming)
                }
            }
        }
    }
}

@Preview
@Composable
fun TabScreenPreview() {
    TabScreen(
        matches = Matches(
            previous = listOf(
                Match("Team A", "2023-01-01", "Description 1", "Highlights 1", "Team B", "Team A"),
                Match("Team A", "2023-02-01", "Description 2", "Highlights 2", "Team D", "Team C")
            ),
            upcoming = listOf(
                Match("Team A", "2023-03-01", "Description 3", "Highlights 3", "Team F", "Team E")
            )
        ),
        teamName = "Team A"
    )

}