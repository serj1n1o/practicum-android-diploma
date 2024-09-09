package ru.practicum.android.diploma.favorites.data

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.domain.api.FavoriteRepository
import ru.practicum.android.diploma.global.db.AppDatabase
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class FavoriteRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val vacancyDbConvertor: VacancyDbConvertor
): FavoriteRepository {
    override suspend fun addFavoriteVacancy(vacancyDetails: VacancyDetails) {
        appDatabase.vacancyDao().insertVacancy(vacancyDbConvertor.map(vacancyDetails))
    }

    override suspend fun deleteFavoriteVacancy(vacancyDetails: VacancyDetails) {
        TODO("Not yet implemented")
    }

    override fun getFavoriteVacancies(): Flow<List<VacancyDetails>> {
        TODO("Not yet implemented")
    }

    override fun getIdsFavoriteVacancies(): Flow<List<String>> {
        TODO("Not yet implemented")
    }
}
