package com.thusee.footballevent.network.model.response

import com.squareup.moshi.Json

data class TeamsResponse(
    @field:Json(name = "teams")
    val teams: List<TeamData>? = emptyList()
)

data class TeamData(
    @field:Json(name = "id")
    val id: String? = null,
    @field:Json(name = "logo")
    val logo: String? = null,
    @field:Json(name = "name")
    val name: String? = null
)