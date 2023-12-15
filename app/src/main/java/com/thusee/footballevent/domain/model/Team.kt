package com.thusee.footballevent.domain.model

import androidx.compose.runtime.Stable

@Stable
data class Team(
    val id: String = "",
    val logo: String = "",
    val name: String = "",
)

