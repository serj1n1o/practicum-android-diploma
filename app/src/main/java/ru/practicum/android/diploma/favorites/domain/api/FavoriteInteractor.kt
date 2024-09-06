package ru.practicum.android.diploma.favorites.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Vacancy

interface FavoriteInteractor {
    fun favoriteVacancy(): Flow<List<Vacancy>>
}
