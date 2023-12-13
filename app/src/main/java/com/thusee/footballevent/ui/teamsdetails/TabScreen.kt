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
import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.ui.components.CustomToolbar
import com.thusee.footballevent.ui.components.horizontal.HorizontalMatchCard
import com.thusee.footballevent.ui.components.vertical.MatchVerticalList

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
                        Text(text = matchTabItem.title)
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
                    MatchVerticalList(matches.previous)
                } else {
                    LazyColumn(content = {
                        items(matches.upcoming) { match ->
                            HorizontalMatchCard(match = match)
                        }
                    })
                }
            }
        }
    }
}