package com.thusee.footballevent.ui.matches

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thusee.footballevent.R
import com.thusee.footballevent.domain.model.Matches
import com.thusee.footballevent.domain.repository.MatchDataRepository
import com.thusee.footballevent.ui.common.UIState
import com.thusee.footballevent.ui.common.errors.errorHandler.ErrorMessageResourceUtil
import com.thusee.footballevent.ui.common.errors.state.ErrorDisplayInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchDataRepository: MatchDataRepository,
    private val errorUtil: ErrorMessageResourceUtil
) : ViewModel(){
    var uiState by mutableStateOf<UIState<Matches, ErrorDisplayInfo>>(UIState.Loading)
        private set

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        val fetchingMatchesException = FetchingMatchesException(R.string.error_fetching_matches)

        viewModelScope.launch {
            val result = matchDataRepository.getMatches()
            uiState = when {
                result.isSuccess && result.getOrDefault(Matches()) == Matches() -> UIState.Empty
                result.isSuccess -> UIState.Success(result.getOrDefault(Matches()))
                result.isFailure -> UIState.Error(
                    ErrorDisplayInfo(
                        errorUtil.getErrorMessageResource(
                            result.exceptionOrNull() ?: fetchingMatchesException
                        )
                    )
                )

                else -> UIState.Error(
                    ErrorDisplayInfo(
                        errorUtil.getErrorMessageResource(
                            result.exceptionOrNull() ?: fetchingMatchesException
                        )
                    )
                )
            }
        }
    }
}