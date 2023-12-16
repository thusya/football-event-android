package com.thusee.footballevent.ui.teamsdetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.ui.navigation.TEAM_ID
import com.thusee.footballevent.ui.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val matchDataRepository: MatchDataRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val teamId: String = savedStateHandle.get<String>(TEAM_ID) ?: ""

    var uiState: MutableState<UIState<Matches>> = mutableStateOf(UIState.Loading)

    init {
        getMatchesDetailsByTeam(teamId)
    }

    private fun getMatchesDetailsByTeam(teamId: String) {
        viewModelScope.launch {
            val result = matchDataRepository.getMatchesByTeamId(teamId)
            uiState.value = when {
                result.isSuccess && result.getOrDefault(Matches()) == Matches() -> UIState.Empty
                result.isSuccess -> UIState.Success(result.getOrDefault(Matches()))
                result.isFailure -> UIState.Error(
                    result.exceptionOrNull() ?: Exception("Unknown error")
                )

                else -> UIState.Error(result.exceptionOrNull() ?: Exception("Unknown error"))
            }
        }
    }

}