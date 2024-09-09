package ru.practicum.android.diploma.favorites.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.favorites.domain.api.FavoriteRepository
import ru.practicum.android.diploma.global.db.AppDatabase
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class FavoriteRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val vacancyDbConvertor: VacancyDbConvertor
): FavoriteRepository {
    override suspend fun addVacancy(vacancyDetails: VacancyDetails) {
        appDatabase.vacancyDao().insertVacancy(vacancyDbConvertor.mapVacancyDetailsToVacancyEntity(vacancyDetails))
    }

    override suspend fun deleteVacancy(vacancyId: String) {
        appDatabase.vacancyDao().deleteVacancy(vacancyId)
    }

    override fun getVacancies(): Flow<List<Vacancy>> = flow {
        val vacancies = appDatabase.vacancyDao().getAllVacancies()
        emit(vacancyDbConvertor.mapListVacancyEntityToListVacancy(vacancies))
    }

    override fun getVacancy(vacancyId: String): Flow<VacancyDetails> = flow {
        val vacancy = appDatabase.vacancyDao().getFavoriteVacancy(vacancyId)
        emit(vacancyDbConvertor.mapVacancyEntityToVacancyDetails(vacancy))
    }

    override fun getIdsVacancies(): Flow<List<String>> {
        TODO("Not yet implemented")
    }
}
