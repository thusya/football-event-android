package com.thusee.footballevent.network.repository.impl

import com.thusee.footballevent.network.api.ApiService
import com.thusee.footballevent.network.model.response.MatchResponse
import com.thusee.footballevent.network.model.response.TeamsResponse
import com.thusee.footballevent.network.repository.FootballDataRemote
import com.thusee.footballevent.network.utils.handleRequest
import javax.inject.Inject

class FootballDataRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : FootballDataRemote {
    override suspend fun getTeamData(): Result<TeamsResponse> {
        return handleRequest { apiService.getTeamData() }
    }

    override suspend fun getMatchData(): Result<MatchResponse> {
        return handleRequest { apiService.getMatchData() }
    }

    override suspend fun getMatchesByTeamId(id: String): Result<MatchResponse> {
        return handleRequest { apiService.getMatchesByTeamId(id) }
    }
}