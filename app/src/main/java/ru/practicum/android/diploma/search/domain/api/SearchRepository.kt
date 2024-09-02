package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.global.util.RequestResult
import ru.practicum.android.diploma.search.data.dto.VacanciesListResponse
import ru.practicum.android.diploma.search.domain.model.SearchQuery

interface SearchRepository {
    fun searchVacancies(
        searchQuery: SearchQuery
    ): Flow<RequestResult<VacanciesListResponse>>
}
