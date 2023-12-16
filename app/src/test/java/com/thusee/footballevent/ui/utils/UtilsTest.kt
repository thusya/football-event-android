package com.thusee.footballevent.ui.utils

import com.thusee.footballevent.domain.model.TeamSplitResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UtilsTest {
    @Test
    fun `splitStringIfStartsWithTeam should split input string correctly when it starts with 'Team '`() {
        val input = "Team Barcelona"

        val result = Utils.splitStringIfStartsWithTeam(input)

        assertEquals(TeamSplitResult("Team", "Barcelona"), result)
    }

    @Test
    fun `splitStringIfStartsWithTeam should handle input without 'Team ' prefix`() {
        val input = "Real Madrid"

        val result = Utils.splitStringIfStartsWithTeam(input)

        assertEquals(TeamSplitResult("Team", "Real Madrid"), result)
    }

    @Test
    fun `splitStringIfStartsWithTeam should handle empty input`() {
        val input = ""

        val result = Utils.splitStringIfStartsWithTeam(input)

        assertEquals(TeamSplitResult("Team", ""), result)
    }
}