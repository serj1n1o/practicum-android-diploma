package ru.practicum.android.diploma.favorites.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.favorites.domain.api.FavoriteRepository
import ru.practicum.android.diploma.global.db.AppDatabase
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class FavoriteRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val vacancyDbConvertor: VacancyDbConvertor,
) : FavoriteRepository {
    override suspend fun addVacancy(vacancyDetails: VacancyDetails) {
        withContext(Dispatchers.IO) {
            appDatabase.vacancyDao().insertVacancy(vacancyDbConvertor.mapVacancyDetailsToVacancyEntity(vacancyDetails))
        }
    }

    override fun getVacancies(): Flow<List<Vacancy>> = flow {
        val vacancies = withContext(Dispatchers.IO) {
            appDatabase.vacancyDao().getAllVacancies()
        }
        emit(vacancyDbConvertor.mapListVacancyEntityToListVacancy(vacancies))
    }

    override suspend fun updateVacancy(vacancyDetails: VacancyDetails) {
        withContext(Dispatchers.IO) {
            appDatabase.vacancyDao().updateVacancy(vacancyDbConvertor.mapVacancyDetailsToVacancyEntity(vacancyDetails))
        }
    }

    override suspend fun deleteVacancy(vacancyId: String) {
        withContext(Dispatchers.IO) {
            appDatabase.vacancyDao().deleteVacancy(vacancyId)
        }
    }

    override fun getVacancy(vacancyId: String): Flow<VacancyDetails> = flow {
        val vacancy = withContext(Dispatchers.IO) {
            appDatabase.vacancyDao().getVacancy(vacancyId)
        }
        emit(vacancyDbConvertor.mapVacancyEntityToVacancyDetails(vacancy))
    }

    override fun getIdsVacancies(): Flow<List<String>> = flow {
        withContext(Dispatchers.IO) {
            appDatabase.vacancyDao().getIdsVacancies()
        }
    }
}
