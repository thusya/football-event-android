package com.thusee.footballevent.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.ui.common.UIState
import com.thusee.footballevent.ui.common.errors.errorHandler.ErrorMessageResourceUtil
import com.thusee.footballevent.ui.common.errors.state.ErrorDisplayInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val matchDataRepository: MatchDataRepository,
    private val errorUtil: ErrorMessageResourceUtil
) : ViewModel() {
    private var _teamsState =
        MutableStateFlow<UIState<List<Team>, ErrorDisplayInfo>>(UIState.Loading)
    val teamsState: StateFlow<UIState<List<Team>, ErrorDisplayInfo>> = _teamsState.asStateFlow()

    init {
        fetchTeams()
    }

    private fun fetchTeams() {
        viewModelScope.launch {
            val result = matchDataRepository.getTeams()
            _teamsState.value = when {
                result.isSuccess && result.getOrNull().isNullOrEmpty() -> UIState.Empty
                result.isSuccess -> UIState.Success(result.getOrNull().orEmpty())
                result.isFailure -> UIState.Error(
                    ErrorDisplayInfo(
                        errorUtil.getErrorMessageResource(
                            result.exceptionOrNull()
                        )
                    )
                )

                else -> UIState.Error(
                    ErrorDisplayInfo(
                        errorUtil.getErrorMessageResource(result.exceptionOrNull())
                    )
                )
            }
        }
    }
}