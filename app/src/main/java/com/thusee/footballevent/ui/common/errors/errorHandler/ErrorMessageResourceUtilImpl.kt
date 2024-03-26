package com.thusee.footballevent.ui.common.errors.errorHandler

import com.thusee.footballevent.R
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorMessageResourceUtilImpl @Inject constructor() : ErrorMessageResourceUtil {
    override fun getErrorMessageResource(error: Throwable): Int {
        val errorMessage = error.message ?: ""

        return when(error) {
            is UnknownHostException -> R.string.error_no_network

            is HttpException -> return when {
                errorMessage.contains("404") -> R.string.error_page_not_found
                errorMessage.contains("500") -> R.string.error_server_down
                else -> R.string.error_common
            }

            else -> R.string.error_common
        }
    }
}