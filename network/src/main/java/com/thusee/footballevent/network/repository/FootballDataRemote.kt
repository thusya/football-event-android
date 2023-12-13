package com.thusee.footballevent.network.repository

import com.thusee.footballevent.network.model.response.MatchResponse
import com.thusee.footballevent.network.model.response.TeamsResponse

interface FootballDataRemote {
    suspend fun getTeamData(): Result<TeamsResponse>
    suspend fun getMatchData(): Result<MatchResponse>
    suspend fun getMatchesByTeamId(id: String): Result<MatchResponse>
}