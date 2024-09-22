package ru.practicum.android.diploma.filter.ui.area

import ru.practicum.android.diploma.filter.domain.model.Location

sealed interface AreaSelectState {
    data object Loading : AreaSelectState

    data class Content(val locations: List<Location>) : AreaSelectState

    data class Error(val error: Int) : AreaSelectState

    data object NotFound : AreaSelectState
}
