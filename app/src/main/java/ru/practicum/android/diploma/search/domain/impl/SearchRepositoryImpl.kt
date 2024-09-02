package ru.practicum.android.diploma.search.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.global.data.network.NetworkClient
import ru.practicum.android.diploma.global.data.network.dto.Request
import ru.practicum.android.diploma.global.util.RequestResult
import ru.practicum.android.diploma.search.data.dto.VacanciesListResponse
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.model.SearchQuery

class SearchRepositoryImpl(private val networkClient: NetworkClient) : SearchRepository {
    override fun searchVacancies(searchQuery: SearchQuery): Flow<RequestResult<VacanciesListResponse>> = flow {
        val request = Request.GetVacancies(HashMap())

        when (val result = networkClient.doRequest(request)) {
            is RequestResult.Success -> {
                // Тут нужен маппер, пока для теста сделано так
                emit(RequestResult.Success(result.data as VacanciesListResponse))
            }

            is RequestResult.Error -> {
                emit(RequestResult.Error(result.error!!))
            }
        }
    }
}
