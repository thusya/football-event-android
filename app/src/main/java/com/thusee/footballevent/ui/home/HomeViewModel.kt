package com.thusee.footballevent.ui.home

import androidx.lifecycle.ViewModel
import com.thusee.footballevent.domain.repository.MatchDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val matchDataRepository: MatchDataRepository
) : ViewModel() {


}