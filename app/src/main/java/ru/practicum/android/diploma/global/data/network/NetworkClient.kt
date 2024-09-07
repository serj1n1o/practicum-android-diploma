package ru.practicum.android.diploma.global.data.network

import ru.practicum.android.diploma.global.data.network.dto.Request
import ru.practicum.android.diploma.global.data.network.dto.Response
import ru.practicum.android.diploma.global.util.RequestResult

interface NetworkClient {
    suspend fun doRequest(request: Request): RequestResult<Response>
}
