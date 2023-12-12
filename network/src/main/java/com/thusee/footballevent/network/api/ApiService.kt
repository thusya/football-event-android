package com.thusee.footballevent.network.api

import com.thusee.footballevent.network.model.response.MatchResponse
import com.thusee.footballevent.network.model.response.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/teams")
    suspend fun getTeamData(): TeamsResponse

    @GET("/teams/matches")
    suspend fun getMatchData(): MatchResponse

    @GET("/teams/{id}/matches")
    suspend fun getMatchesByTeamId(@Path("id") id: String): MatchResponse
}