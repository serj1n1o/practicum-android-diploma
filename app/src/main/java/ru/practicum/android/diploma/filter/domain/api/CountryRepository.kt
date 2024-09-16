package ru.practicum.android.diploma.filter.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.global.util.RequestResult

interface CountryRepository {
    fun searchRegion(): Flow<RequestResult<List<Country>>>
}
