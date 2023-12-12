package com.thusee.footballevent.domain.model

import androidx.compose.runtime.Stable
import com.thusee.footballevent.network.model.response.TeamsResponse

@Stable
data class Team(
    val id: String = "",
    val logo: String = "",
    val name: String = "",
)

fun TeamsResponse.toTeams(): List<Team> = teams?.map { teamData ->
    Team(
        id = teamData.id.orEmpty(),
        logo = teamData.logo.orEmpty(),
        name = teamData.name.orEmpty()
    )
} ?: emptyList()

