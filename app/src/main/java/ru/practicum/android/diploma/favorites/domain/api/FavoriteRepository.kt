package ru.practicum.android.diploma.favorites.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

interface FavoriteRepository {

    suspend fun addVacancy(vacancyDetails: VacancyDetails)

    fun getVacancies(): Flow<List<Vacancy>>

    suspend fun updateVacancy(vacancyDetails: VacancyDetails)

    suspend fun deleteVacancy(vacancyId: String)

    fun getVacancy(vacancyId: String): Flow<VacancyDetails>

    fun getIdsVacancies(): Flow<List<String>>

}
