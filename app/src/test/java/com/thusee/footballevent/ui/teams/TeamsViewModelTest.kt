package com.thusee.footballevent.ui.teams

import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.ui.utils.UIState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TeamsViewModelTest {
    private val matchDataRepository: MatchDataRepository = mockk(relaxed = true)
    private lateinit var teamsViewModel: TeamsViewModel

    @Test
    fun `fetchTeams with success should update teamsState to Success`() =
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            Dispatchers.setMain(testDispatcher)

            val mockTeams = listOf(Team("Team A"), Team("Team B"))
            coEvery { matchDataRepository.getTeams() } returns Result.success(mockTeams)

            teamsViewModel = TeamsViewModel(matchDataRepository)

            assertEquals(UIState.Success(mockTeams), teamsViewModel.teamsState.value)

            Dispatchers.resetMain()
        }

    @Test
    fun `fetchTeams with empty result should update teamsState to Empty`() =
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            Dispatchers.setMain(testDispatcher)

            coEvery { matchDataRepository.getTeams() } returns Result.success(emptyList())

            teamsViewModel = TeamsViewModel(matchDataRepository)

            assertEquals(UIState.Empty, teamsViewModel.teamsState.value)
        }

    @Test
    fun `fetchTeams with failure should update teamsState to Error`() =
        runTest {
            val error = Exception("Some error")
            coEvery { matchDataRepository.getTeams() } returns Result.failure(error)

            teamsViewModel = TeamsViewModel(matchDataRepository)

            assertEquals(UIState.Error(error), teamsViewModel.teamsState.value)
        }
}
