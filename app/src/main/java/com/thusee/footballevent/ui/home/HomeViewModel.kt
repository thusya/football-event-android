package com.thusee.footballevent.ui.home

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
class HomeViewModel @Inject constructor(
    private val matchDataRepository: MatchDataRepository
) : ViewModel() {
    private val _teamsState = MutableStateFlow<UIState<List<Team>>>(UIState.Loading)
    val teamsState: StateFlow<UIState<List<Team>>> = _teamsState.asStateFlow()

    init {
        fetchTeams()
    }

    private fun fetchTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val teams = matchDataRepository.getTeams()
                if (teams.isEmpty()) {
                    _teamsState.value = UIState.Empty
                } else {
                    _teamsState.value = UIState.Success(teams)
                }
            } catch (e: Exception) {
                _teamsState.value = UIState.Error(e)
            }
        }
    }
}