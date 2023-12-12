package com.thusee.footballevent.data.repository

import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.domain.model.toMatches
import com.thusee.footballevent.domain.model.toTeams
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.network.api.ApiService
import javax.inject.Inject

class MatchDataRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MatchDataRepository {
    override suspend fun getTeams(): List<Team> = apiService.getTeamData().toTeams()

    override suspend fun getMatches(): Matches = apiService.getMatchData().toMatches()

    override suspend fun getMatchesByTeamId(id: String): Matches =
        apiService.getMatchesByTeamId(id).toMatches()
}