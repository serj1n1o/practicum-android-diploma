package ru.practicum.android.diploma.favorites.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.domain.api.FavoriteInteractor
import ru.practicum.android.diploma.favorites.domain.api.FavoriteRepository
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class FavoriteInteractorImpl(private val favoriteRepository: FavoriteRepository) : FavoriteInteractor {

    override suspend fun addVacancy(vacancyDetails: VacancyDetails) {
        favoriteRepository.addVacancy(vacancyDetails)
    }

    override fun getVacancy(vacancyId: String): Flow<VacancyDetails> {
        return favoriteRepository.getVacancy(vacancyId)
    }

    override suspend fun deleteVacancy(vacancyId: String) {
        favoriteRepository.deleteVacancy(vacancyId)
    }

    override fun getVacancies(): Flow<List<Vacancy>> {
        return favoriteRepository.getVacancies()
    }

    override fun getIdsVacancies(): Flow<List<String>> {
        return favoriteRepository.getIdsVacancies()
    }
}
