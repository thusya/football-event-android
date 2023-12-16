package com.thusee.footballevent.data.mapper

import com.thusee.footballevent.domain.model.Match
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.network.model.response.MatchData
import com.thusee.footballevent.network.model.response.MatchResponse
import com.thusee.footballevent.network.model.response.TeamsResponse

fun MatchResponse.toMatches(): Matches {
    val prevMatches = matches?.previous?.map { it.toMatch() } ?: emptyList()
    val upMatches = matches?.upcoming?.map { it.toMatch() } ?: emptyList()

    return Matches(
        previous = prevMatches,
        upcoming = upMatches
    )
}

fun MatchData.toMatch(): Match = Match(
    away = away.orEmpty(),
    date = date.orEmpty(),
    description = description.orEmpty(),
    highlights = highlights.orEmpty(),
    home = home.orEmpty(),
    winner = winner.orEmpty()
)

fun TeamsResponse.toTeams(): List<Team> = teams?.map { teamData ->
    Team(
        id = teamData.id.orEmpty(),
        logo = teamData.logo.orEmpty(),
        name = teamData.name.orEmpty()
    )
} ?: emptyList()

