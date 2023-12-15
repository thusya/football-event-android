package com.thusee.footballevent.domain.model

import androidx.compose.runtime.Stable

@Stable
data class Match(
    val away: String = "",
    val date: String = "",
    val description: String = "",
    val highlights: String = "",
    val home: String = "",
    val winner: String = "",
)

data class Matches(
    @Stable val previous: List<Match> = emptyList(),
    @Stable val upcoming: List<Match> = emptyList(),
)

fun MatchResponse.toMatches(): Matches {
    val prevMatches = matches?.previous?.map { it.toMatch() } ?: emptyList()
    val upMatches = matches?.upcoming?.map { it.toMatch() } ?: emptyList()

    return Matches(
        previous = prevMatches,
        upcoming = upMatches
    )
}
