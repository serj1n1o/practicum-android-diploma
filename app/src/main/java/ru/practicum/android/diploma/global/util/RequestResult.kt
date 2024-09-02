package ru.practicum.android.diploma.global.util

sealed class RequestResult<T>(val data: T? = null, val error: Int? = null) {

    class Success<T>(data: T) : RequestResult<T>(data)
    class Error<T>(type: Int, data: T? = null) : RequestResult<T>(data, type)
}
