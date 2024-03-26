package com.thusee.footballevent.ui.common

sealed class UIState<out T, out E> {
    data object Loading : UIState<Nothing, Nothing>()
    data class Success<T>(val data: T) : UIState<T, Nothing>()
    data class Error<E>(val errorData: E) : UIState<Nothing, E>()
    data object Empty : UIState<Nothing, Nothing>()
}