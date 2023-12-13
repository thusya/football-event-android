package com.thusee.footballevent.ui.utils

import com.thusee.footballevent.domain.model.TeamSplitResult

object Utils {
    fun splitStringIfStartsWithTeam(input: String): TeamSplitResult {
        return if (input.startsWith("Team ")) {
            val splitStrings = input.split(" ", limit = 2)
            TeamSplitResult(splitStrings[0], splitStrings.getOrNull(1) ?: "")
        } else {
            TeamSplitResult("Team", input)
        }
    }
}