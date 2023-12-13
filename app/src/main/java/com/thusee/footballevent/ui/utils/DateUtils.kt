package com.thusee.footballevent.ui.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    private val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    private val dateFormatter = SimpleDateFormat("dd-MM-yy", Locale.US)
    private val timeFormatter = SimpleDateFormat("HH:mm a", Locale.US)

    fun convertDateTime(dateTimeString: String): Pair<String, String> {
        return try {
            val date = parser.parse(dateTimeString)
            val formattedDate = dateFormatter.format(date)
            val formattedTime = timeFormatter.format(date)
            formattedDate to formattedTime
        } catch (e: ParseException) {
            "Invalid Date" to "Invalid Time"
        }
    }
}