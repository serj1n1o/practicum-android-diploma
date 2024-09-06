package ru.practicum.android.diploma.db.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Vacancy

interface FavoriteInteractor {
    fun favoriteVacancy(): Flow<List<Vacancy>>
}
