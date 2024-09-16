package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.favorites.data.VacancyDbConvertor
import ru.practicum.android.diploma.global.data.network.NetworkClient
import ru.practicum.android.diploma.global.data.network.dto.Request
import ru.practicum.android.diploma.global.db.AppDatabase
import ru.practicum.android.diploma.global.util.RequestResult
import ru.practicum.android.diploma.global.util.ResponseCodes
import ru.practicum.android.diploma.search.data.dto.VacanciesListResponse
import ru.practicum.android.diploma.search.data.dto.details.VacancyResponse
import ru.practicum.android.diploma.search.data.mapper.VacancyMapper
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.model.SearchQuery
import ru.practicum.android.diploma.search.domain.model.VacancyList
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class SearchRepositoryImpl(
    private val networkClient: NetworkClient,
    private val vacancyMapper: VacancyMapper,
    private val database: AppDatabase,
    private val vacancyDbConvertor: VacancyDbConvertor,
) : SearchRepository {
    override fun searchVacancies(searchQuery: SearchQuery): Flow<RequestResult<VacancyList>> = flow {
        val body: HashMap<String, String> = HashMap()
        if (searchQuery.text != null) {
            body["text"] = searchQuery.text!!
        }
        body["page"] = searchQuery.page.toString()
        body["per_page"] = searchQuery.perPage.toString()
        val request = Request.GetVacancies(body)

        when (val result = networkClient.doRequest(request)) {
            is RequestResult.Success -> {
                emit(RequestResult.Success(vacancyMapper.map(result.data as VacanciesListResponse)))
            }

            is RequestResult.Error -> {
                emit(RequestResult.Error(result.error!!))
            }
        }
    }

    override fun getVacancy(vacancyId: String): Flow<RequestResult<VacancyDetails>> = flow {
        val request = Request.GetVacancyById(vacancyId)
        val favoritesVacancyId = withContext(Dispatchers.IO) {
            database.vacancyDao().getIdsVacancies()
        }
        val inFavorite = vacancyId in favoritesVacancyId

        when (val result = networkClient.doRequest(request)) {
            is RequestResult.Success -> {
                val vacancy = vacancyMapper.map(
                    vacancy = result.data as VacancyResponse,
                    inFavorite = inFavorite
                )
                if (inFavorite) {
                    database.vacancyDao().updateVacancy(vacancyDbConvertor.mapVacancyDetailsToVacancyEntity(vacancy))
                }
                emit(RequestResult.Success(vacancy))
            }

            is RequestResult.Error -> {
                if (inFavorite && result.error == ResponseCodes.CODE_VACANCY_HAVE_NOT) {
                    withContext(Dispatchers.IO) {
                        database.vacancyDao().deleteVacancy(vacancyId)
                    }
                }

                if (inFavorite && result.error == ResponseCodes.CODE_NO_CONNECT) {
                    val vacancy = withContext(Dispatchers.IO) {
                        database.vacancyDao().getVacancy(vacancyId)
                    }
                    emit(RequestResult.Success(vacancyDbConvertor.mapVacancyEntityToVacancyDetails(vacancy)))
                } else {
                    emit(RequestResult.Error(result.error!!))
                }
            }
        }
    }
}