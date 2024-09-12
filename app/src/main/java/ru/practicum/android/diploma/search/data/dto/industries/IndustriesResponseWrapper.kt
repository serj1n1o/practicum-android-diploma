package ru.practicum.android.diploma.search.data.dto.industries

import ru.practicum.android.diploma.global.data.network.dto.Response

data class IndustriesResponseWrapper(
    val industries: IndustriesResponse,
) : Response()
