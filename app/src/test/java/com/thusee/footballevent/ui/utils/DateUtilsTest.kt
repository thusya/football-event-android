package com.thusee.footballevent.ui.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DateUtilsTest {
    @Test
    fun `convertDateTime should correctly convert a valid date-time string`() {
        val dateTimeString = "2023-12-31T12:30:00.000Z"

        val result = DateUtils.convertDateTime(dateTimeString)

        assertEquals("31-12-23" to "12:30 PM", result)
    }

    @Test
    fun `convertDateTime should handle invalid date-time strings`() {
        val invalidDateTimeString = "InvalidDateTime"

        val result = DateUtils.convertDateTime(invalidDateTimeString)

        assertEquals("Invalid Date" to "Invalid Time", result)
    }

    @Test
    fun `convertDateTime should handle null input`() {
        val nullDateTimeString: String? = null

        val result = DateUtils.convertDateTime(nullDateTimeString ?: "")

        assertEquals("Invalid Date" to "Invalid Time", result)
    }
}