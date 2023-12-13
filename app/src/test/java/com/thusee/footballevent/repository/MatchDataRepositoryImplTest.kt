package com.thusee.footballevent.repository

import com.thusee.footballevent.data.repository.MatchDataRepositoryImpl
import com.thusee.footballevent.domain.model.Match
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.network.model.response.MatchData
import com.thusee.footballevent.network.model.response.MatchResponse
import com.thusee.footballevent.network.model.response.MatchesData
import com.thusee.footballevent.network.model.response.TeamData
import com.thusee.footballevent.network.model.response.TeamsResponse
import com.thusee.footballevent.network.repository.FootballDataRemote
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class MatchDataRepositoryImplTest {
    private lateinit var matchDataRepo: MatchDataRepository

    private val teamResponse = TeamsResponse(
        listOf(
            TeamData("1", "logo1", "Team One"),
            TeamData("2", "logo2", "Team Two")
        )
    )

    private val matchResponse = MatchResponse(
        matches = MatchesData(
            previous = listOf(
                MatchData("Team A", "2023-01-01", "Description 1", "Highlights 1", "Team B", "Team A"),
                MatchData("Team C", "2023-02-01", "Description 2", "Highlights 2", "Team D", "Team C")
            ),
            upcoming = listOf(
                MatchData("Team E", "2023-03-01", "Description 3", "Highlights 3", "Team F", "Team E")
            )
        )
    )

    @Before
    fun before() {
        val footBallDataRemote = mockk<FootballDataRemote>(relaxed = true)

        coEvery { footBallDataRemote.getMatchData() } returns Result.success(matchResponse)
        coEvery { footBallDataRemote.getTeamData() } returns Result.success(teamResponse)
        coEvery { footBallDataRemote.getMatchesByTeamId(any()) } returns Result.success(matchResponse)

        matchDataRepo = MatchDataRepositoryImpl(footBallDataRemote)
    }

    @Test
    fun `getTeams returns expected`() = runTest {

        val expected = listOf(
            Team("1", "logo1", "Team One"),
            Team("2", "logo2", "Team Two")
        )

        assertEquals(expected, matchDataRepo.getTeams().getOrNull())
    }

    @Test
    fun `getMatches returns expected data`() = runTest {
        val expected = Matches(
            previous = listOf(
                Match("Team A", "2023-01-01", "Description 1", "Highlights 1", "Team B", "Team A"),
                Match("Team C", "2023-02-01", "Description 2", "Highlights 2", "Team D", "Team C")
            ),
            upcoming = listOf(
                Match("Team E", "2023-03-01", "Description 3", "Highlights 3", "Team F", "Team E")
            )
        )

        assertEquals(expected, matchDataRepo.getMatches().getOrNull())
    }

    @Test
    fun `getMatchesByTeam returns expectedData`() = runTest {
        val expected = Matches(
            previous = listOf(
                Match("Team A", "2023-01-01", "Description 1", "Highlights 1", "Team B", "Team A"),
                Match("Team C", "2023-02-01", "Description 2", "Highlights 2", "Team D", "Team C")
            ),
            upcoming = listOf(
                Match("Team E", "2023-03-01", "Description 3", "Highlights 3", "Team F", "Team E")
            )
        )

        assertEquals(expected, matchDataRepo.getMatchesByTeamId("12").getOrNull())
    }

}