package ru.practicum.android.diploma.search.data.dto.regions

import ru.practicum.android.diploma.global.data.network.dto.Response

data class AreasResponseWrapper(
    val areas: AreasResponse,
) : Response()
