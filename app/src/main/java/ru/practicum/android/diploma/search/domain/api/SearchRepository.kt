package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.global.util.RequestResult
import ru.practicum.android.diploma.search.domain.model.SearchQuery
import ru.practicum.android.diploma.search.domain.model.VacancyList
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

interface SearchRepository {
    fun searchVacancies(
        searchQuery: SearchQuery,
    ): Flow<RequestResult<VacancyList>>

    fun getVacancy(vacancyId: String): Flow<RequestResult<VacancyDetails>>
}
