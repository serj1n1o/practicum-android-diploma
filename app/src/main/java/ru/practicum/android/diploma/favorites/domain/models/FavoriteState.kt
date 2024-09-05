package ru.practicum.android.diploma.favorites.domain.models

import ru.practicum.android.diploma.search.domain.model.Vacancy

sealed interface FavoriteState {

    data class Content(
        val vacancies: List<Vacancy>
    ): FavoriteState

    data object Empty : FavoriteState
}
