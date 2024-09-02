package ru.practicum.android.diploma.global.util

sealed class RequestResult<T>(val data: T? = null, val error: ErrorEnum? = null, errTxt: String? = null) {

    class Success<T>(data: T) : RequestResult<T>(data)
    class Error<T>(type: ErrorEnum, data: T? = null, errTxt: String? = null) : RequestResult<T>(data, type, errTxt)
}

enum class ErrorEnum {
    NO_INTERNET,
    HTTP_ERROR,
    SERVER_ERROR,
}
