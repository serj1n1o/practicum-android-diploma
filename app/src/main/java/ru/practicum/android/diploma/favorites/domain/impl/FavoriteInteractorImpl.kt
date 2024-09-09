package ru.practicum.android.diploma.favorites.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.favorites.domain.api.FavoriteInteractor
import ru.practicum.android.diploma.favorites.domain.api.FavoriteRepository
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class FavoriteInteractorImpl(private val favoriteRepository: FavoriteRepository) : FavoriteInteractor {
    override fun favoriteVacancy(): Flow<List<Vacancy>> {
        return flow<List<Vacancy>> {
            val list = 1
            // написал так только чтоб не крашилось
        }
    }

    override suspend fun addFavoriteVacancy(vacancyDetails: VacancyDetails) {
        favoriteRepository.addFavoriteVacancy(vacancyDetails)
    }

    override suspend fun deleteFavoriteVacancy(vacancyDetails: VacancyDetails) {
        favoriteRepository.deleteFavoriteVacancy(vacancyDetails)
    }

    override fun getFavoriteVacancies(): Flow<List<VacancyDetails>> {
        return favoriteRepository.getFavoriteVacancies()
    }

    override fun getIdsFavoriteVacancies(): Flow<List<String>> {
        return favoriteRepository.getIdsFavoriteVacancies()
    }
}
