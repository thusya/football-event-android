package com.thusee.footballevent.ui.matches

import com.thusee.footballevent.domain.model.Match
import com.thusee.footballevent.domain.model.Matches
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
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MatchesViewModelTest {
    private val matchDataRepository: MatchDataRepository = mockk(relaxed = true)
    private lateinit var matchesViewModel: MatchesViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchMatches with success should update uiState to Success`() = runTest {

        val mockMatches = Matches(
            previous = listOf(
                Match("Team A", "2023-01-01", "Description 1", "Highlights 1", "Team B", "Team A"),
                Match("Team C", "2023-02-01", "Description 2", "Highlights 2", "Team D", "Team C")
            ),
            upcoming = listOf(
                Match("Team E", "2023-03-01", "Description 3", "Highlights 3", "Team F", "Team E")
            )
        )
        coEvery { matchDataRepository.getMatches() } returns Result.success(mockMatches)

        matchesViewModel = MatchesViewModel(matchDataRepository)

        assertEquals(UIState.Success(mockMatches), matchesViewModel.uiState.value)
    }

    @Test
    fun `fetchMatches with empty result should update uiState to Empty`() = runTest {

        val emptyMatches = Matches()
        coEvery { matchDataRepository.getMatches() } returns Result.success(emptyMatches)

        matchesViewModel = MatchesViewModel(matchDataRepository)

        assertEquals(UIState.Empty, matchesViewModel.uiState.value)
    }

    @Test
    fun `fetchMatches with failure should update uiState to Error`() = runTest {
        val error = Exception("Some error")
        coEvery { matchDataRepository.getMatches() } returns Result.failure(error)

        matchesViewModel = MatchesViewModel(matchDataRepository)

        assertEquals(UIState.Error(error), matchesViewModel.uiState.value)
    }
}