package com.thusee.footballevent.ui.matches

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.ui.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchDataRepository: MatchDataRepository
) : ViewModel() {

    var uiState: MutableState<UIState<Matches>> = mutableStateOf(UIState.Loading)

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            val result = matchDataRepository.getMatches()
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