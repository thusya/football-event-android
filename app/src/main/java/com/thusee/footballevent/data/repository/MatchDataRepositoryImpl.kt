package com.thusee.footballevent.data.repository

import com.thusee.footballevent.data.mapper.toMatches
import com.thusee.footballevent.data.mapper.toTeams
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.network.repository.FootballDataRemote
import javax.inject.Inject

class MatchDataRepositoryImpl @Inject constructor(
    private val footballDataRemote: FootballDataRemote
) : MatchDataRepository {

    override suspend fun getTeams(): Result<List<Team>> {
        return footballDataRemote.getTeamData().fold(
            onSuccess = { data -> Result.success(data.toTeams()) },
            onFailure = { exception -> Result.failure(exception) },
        )
    }

    override suspend fun getMatches(): Result<Matches> {
        return footballDataRemote.getMatchData().fold(
            onSuccess = { data -> Result.success(data.toMatches()) },
            onFailure = { exception -> Result.failure(exception) },
        )
    }

    override suspend fun getMatchesByTeamId(id: String): Result<Matches> {
        return footballDataRemote.getMatchesByTeamId(id).fold(
            onSuccess = { data -> Result.success(data.toMatches()) },
            onFailure = { exception -> Result.failure(exception) }
        )
    }
}