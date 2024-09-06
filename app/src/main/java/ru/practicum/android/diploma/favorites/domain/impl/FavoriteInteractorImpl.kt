package ru.practicum.android.diploma.favorites.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.favorites.domain.api.FavoriteInteractor
import ru.practicum.android.diploma.search.domain.model.Vacancy

class FavoriteInteractorImpl : FavoriteInteractor {
    override fun favoriteVacancy(): Flow<List<Vacancy>> {
        return flow<List<Vacancy>> {// TODO: написал так только чтоб не крашилось }
        }
    }
}
