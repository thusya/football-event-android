package com.thusee.footballevent.domain.repository

import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.model.Team

interface MatchDataRepository {

    suspend fun getTeams(): Result<List<Team>>
    suspend fun getMatches(): Result<Matches>
    suspend fun getMatchesByTeamId(id: String): Result<Matches>
}