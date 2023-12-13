package com.thusee.footballevent.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.footballevent.domain.model.Team
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.ui.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val matchDataRepository: MatchDataRepository
) : ViewModel() {
    private val _teamsState = MutableStateFlow<UIState<List<Team>>>(UIState.Loading)
    val teamsState: StateFlow<UIState<List<Team>>> = _teamsState.asStateFlow()

    init {
        fetchTeams()
    }

    private fun fetchTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = matchDataRepository.getTeams()
            _teamsState.value = when {
                result.isSuccess && result.getOrNull().isNullOrEmpty() -> UIState.Empty
                result.isSuccess -> UIState.Success(result.getOrNull().orEmpty())
                result.isFailure -> UIState.Error(
                    result.exceptionOrNull() ?: Exception("Unknown error")
                )

                else -> UIState.Error(Exception("Error fetching teams"))
            }
        }
    }
}