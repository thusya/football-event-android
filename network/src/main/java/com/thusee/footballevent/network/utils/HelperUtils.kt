package com.thusee.footballevent.network.utils

suspend fun <T: Any> handleRequest(requestFunc: suspend () -> T): Result<T> {
    return try {
        Result.success(requestFunc.invoke())
    } catch (ex: Exception) {
        Result.failure(ex)
    }
}