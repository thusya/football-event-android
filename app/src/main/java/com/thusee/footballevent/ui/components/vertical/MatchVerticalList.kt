package com.thusee.footballevent.ui.components.vertical

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.thusee.footballevent.domain.model.Match

@Composable
fun MatchVerticalList(matchList: List<Match>?) {
    matchList?.let {
        LazyColumn(content = {
            items(it) { match ->
                VerticalMatchCard(match = match)
            }
        })
    }
}