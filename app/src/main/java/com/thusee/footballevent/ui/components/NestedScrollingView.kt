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
import androidx.compose.ui.unit.dp
import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.ui.components.horizontal.HorizontalList
import com.thusee.footballevent.ui.components.vertical.VerticalMatchCard

@Composable
fun NestedScrollingView(
    matches: Matches
) {
    LazyColumn(
        modifier = Modifier
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

            HorizontalList(matches.previous)

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