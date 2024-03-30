package com.thusee.footballevent.ui.teams

import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.ui.common.UIState
import com.thusee.footballevent.ui.common.errors.errorHandler.ErrorMessageResourceUtil
import com.thusee.footballevent.ui.common.errors.state.ErrorDisplayInfo
import com.thusee.footballevent.ui.utils.MainCoroutineExtension
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class TeamsViewModelTest {
    private val matchDataRepository: MatchDataRepository = mockk(relaxed = true)
    private lateinit var teamsViewModel: TeamsViewModel
    private val errorUtil: ErrorMessageResourceUtil = mockk(relaxed = true)

    @Test
    fun `fetchTeams with success should update teamsState to Success`() =
        runTest {
            val mockTeams = listOf(Team("Team A"), Team("Team B"))
            coEvery { matchDataRepository.getTeams() } returns Result.success(mockTeams)

            teamsViewModel = TeamsViewModel(matchDataRepository, errorUtil)

            assertEquals(UIState.Success(mockTeams), teamsViewModel.teamsState.value)
        }

    @Test
    fun `fetchTeams with empty result should update teamsState to Empty`() =
        runTest {
            coEvery { matchDataRepository.getTeams() } returns Result.success(emptyList())

            teamsViewModel = TeamsViewModel(matchDataRepository, errorUtil)

            assertEquals(UIState.Empty, teamsViewModel.teamsState.value)
        }

    @Test
    fun `fetchTeams with failure should update teamsState to Error`() =
        runTest {
            val error = Exception("Some error")
            coEvery { matchDataRepository.getTeams() } returns Result.failure(error)
            every { errorUtil.getErrorMessageResource(error) } returns R.string.error_common

            teamsViewModel = TeamsViewModel(matchDataRepository, errorUtil)

            assertEquals(R.string.error_common, (teamsViewModel.teamsState.value as UIState.Error<ErrorDisplayInfo>).errorData.messageResource)
        }
}
