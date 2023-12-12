package com.thusee.footballevent.ui.utils

sealed class UIState<out T> {
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val exception: Throwable) : UIState<Nothing>()
    data object Empty : UIState<Nothing>()
}