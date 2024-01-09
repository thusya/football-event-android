package com.thusee.footballevent.ui.teamsdetails

import androidx.lifecycle.SavedStateHandle
import com.thusee.footballevent.domain.model.Match
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.ui.navigation.TEAM_ID
import com.thusee.footballevent.ui.utils.MainCoroutineExtension
import com.thusee.footballevent.ui.utils.UIState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class DetailsViewModelTest {
    private val matchDataRepository: MatchDataRepository = mockk(relaxed = true)
    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)
    private lateinit var detailsViewModel: DetailsViewModel

    @Test
    fun `getMatchesDetailsByTeam with success should update uiState to Success`() = runTest {
        val teamId = "Team123"
        val mockMatches = Matches(
            previous = listOf(
                Match("Team A", "2023-01-01", "Description 1", "Highlights 1", "Team B", "Team A"),
                Match("Team C", "2023-02-01", "Description 2", "Highlights 2", "Team D", "Team C")
            ),
            upcoming = listOf(
                Match("Team E", "2023-03-01", "Description 3", "Highlights 3", "Team F", "Team E")
            )
        )
        coEvery { savedStateHandle.get<String>(TEAM_ID) } returns teamId
        coEvery { matchDataRepository.getMatchesByTeamId(teamId) } returns Result.success(mockMatches)

        detailsViewModel = DetailsViewModel(matchDataRepository, savedStateHandle)

        assertEquals(UIState.Success(mockMatches), detailsViewModel.uiState)
    }

    @Test
    fun `getMatchesDetailsByTeam with empty result should update uiState to Empty`() = runTest {
        val teamId = "Team123"
        val emptyMatches = Matches()
        coEvery { savedStateHandle.get<String>(TEAM_ID) } returns teamId
        coEvery { matchDataRepository.getMatchesByTeamId(teamId) } returns Result.success(emptyMatches)

        detailsViewModel = DetailsViewModel(matchDataRepository, savedStateHandle)

        assertEquals(UIState.Empty, detailsViewModel.uiState)
    }

    @Test
    fun `getMatchesDetailsByTeam with failure should update uiState to Error`() = runTest {
        val teamId = "Team123"
        val error = Exception("Some error")
        coEvery { savedStateHandle.get<String>(TEAM_ID) } returns teamId
        coEvery { matchDataRepository.getMatchesByTeamId(teamId) } returns Result.failure(error)

        detailsViewModel = DetailsViewModel(matchDataRepository, savedStateHandle)

        assertEquals(UIState.Error(error), detailsViewModel.uiState)
    }
}
