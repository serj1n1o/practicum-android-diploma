package ru.practicum.android.diploma.global.util

sealed class RequestResult<T>(val data: T? = null, val error: Int? = null) {

    class Success<T>(data: T) : RequestResult<T>(data)
    class Error<T>(errorCode: Int) : RequestResult<T>(error = errorCode)
}
