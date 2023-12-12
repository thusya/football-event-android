package com.thusee.footballevent.domain.repository

import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.model.Team

interface MatchDataRepository {

    suspend fun getTeams(): List<Team>
    suspend fun getMatches(): Matches
    suspend fun getMatchesByTeamId(id: String): Matches
}