package com.thusee.footballevent.network.model.response

import com.squareup.moshi.Json

data class MatchResponse(
    @field:Json(name = "matches")
    val matches: MatchesData? = null
)

data class MatchesData(
    @field:Json(name = "previous")
    val previous: List<MatchData>? = null,
    @field:Json(name = "upcoming")
    val upcoming: List<MatchData>? = null
)

data class MatchData(
    @field:Json(name = "away")
    val away: String? = null,
    @field:Json(name = "date")
    val date: String? = null,
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "highlights")
    val highlights: String? = null,
    @field:Json(name = "home")
    val home: String? = null,
    @field:Json(name = "winner")
    val winner: String? = null
)