package ru.practicum.android.diploma.favorites.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

interface FavoriteInteractor {
    suspend fun addVacancy(vacancyDetails: VacancyDetails)

    fun getVacancy(vacancyId: String): Flow<VacancyDetails>

    fun updateVacancy(vacancyDetails: VacancyDetails)

    suspend fun deleteVacancy(vacancyId: String)

    fun getVacancies(): Flow<List<Vacancy>>

    fun getIdsVacancies(): Flow<List<String>>
}
