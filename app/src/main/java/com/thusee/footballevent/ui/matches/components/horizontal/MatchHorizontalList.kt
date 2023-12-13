package com.thusee.footballevent.ui.matches.components.horizontal

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.thusee.footballevent.domain.model.Match

@Composable
fun HorizontalList(matchList: List<Match>?) {
    matchList?.let {
        LazyRow(content = {
            items(it) { match ->
                HorizontalMatchCard(
                    match
                )
            }
        })
    }
}