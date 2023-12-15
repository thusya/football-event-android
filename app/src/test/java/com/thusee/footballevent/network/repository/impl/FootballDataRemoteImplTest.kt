package com.thusee.footballevent.network.repository.impl

import com.thusee.footballevent.network.api.ApiService
import com.thusee.footballevent.network.model.response.MatchResponse
import com.thusee.footballevent.network.model.response.TeamsResponse
import com.thusee.footballevent.network.repository.FootballDataRemote
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FootballDataRemoteImplTest {
    private lateinit var footballDataRemote: FootballDataRemote
    private val apiService = mockk<ApiService>(relaxed = true)

    @BeforeEach
    fun setUp() {
        val teamsResponseMock = TeamsResponse()
        coEvery { apiService.getTeamData() } returns teamsResponseMock

        val matchResponseMock = MatchResponse()
        coEvery { apiService.getMatchData() } returns matchResponseMock
        coEvery { apiService.getMatchesByTeamId(any()) } returns matchResponseMock

        footballDataRemote = FootballDataRemoteImpl(apiService)
    }

    @Test
    fun `getTeamData returns success on successful response`() = runTest {
        val result = footballDataRemote.getTeamData()
        assertTrue(result.isSuccess)
    }

    @Test
    fun `getMatchData returns success on successful response`() = runTest {
        val result = footballDataRemote.getMatchData()
        assertTrue(result.isSuccess)
    }

    @Test
    fun `getMatchesByTeamId returns success on successful response`() = runTest {
        val result = footballDataRemote.getMatchesByTeamId("teamId")
        assertTrue(result.isSuccess)
    }

    @Test
    fun `getTeamData returns failure on API failure`() = runTest {
        val exception = RuntimeException("API Error")
        coEvery { apiService.getTeamData() } throws exception

        val result = footballDataRemote.getTeamData()
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}