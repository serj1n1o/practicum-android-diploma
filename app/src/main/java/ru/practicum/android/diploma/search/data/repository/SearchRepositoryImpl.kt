package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.global.data.network.NetworkClient
import ru.practicum.android.diploma.global.data.network.dto.Request
import ru.practicum.android.diploma.global.util.RequestResult
import ru.practicum.android.diploma.search.data.VacancyConverter
import ru.practicum.android.diploma.search.data.dto.VacanciesListResponse
import ru.practicum.android.diploma.search.data.dto.details.VacancyResponse
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.model.SearchQuery
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

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

    override fun getVacancy(vacancyId: String): Flow<RequestResult<VacancyDetails>> = flow {
        val request = Request.GetVacancyById(vacancyId)
        when (val result = networkClient.doRequest(request)) {
            is RequestResult.Success -> {
                val favoritesVacancyId = withContext(Dispatchers.IO) {
                    // database.vacancyDao.getListId()
                    listOf("2132131", "41424")
                }
                val vacancy = converter.mapVacancyResponseToVacancyDetails(
                    vacancy = result.data as VacancyResponse,
                    inFavorite = result.data.id in favoritesVacancyId
                )

                emit(RequestResult.Success(vacancy))
            }

            is RequestResult.Error -> {
                emit(RequestResult.Error(result.error!!))
            }
        }
    }

    private val converter = VacancyConverter() // передавать в конструктор надо будет
}
