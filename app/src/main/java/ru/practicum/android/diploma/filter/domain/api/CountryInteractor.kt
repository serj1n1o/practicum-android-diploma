package ru.practicum.android.diploma.filter.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.model.Country

interface CountryInteractor {
    fun searchIndustries(): Flow<Pair<List<Country>?, Int?>>
}
