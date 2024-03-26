package com.thusee.footballevent.ui.common.errors.errorHandler

interface ErrorMessageResourceUtil {
    fun getErrorMessageResource(error: Throwable): Int
}