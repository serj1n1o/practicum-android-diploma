package ru.practicum.android.diploma.search.ui

import ru.practicum.android.diploma.search.domain.model.VacancyList

sealed interface SearchState {
    object Loading : SearchState

    object LoadingNewPage : SearchState

    data class Content(val vacancies: VacancyList) : SearchState

    data class Error(val error: Int) : SearchState

    object EmptyEditText : SearchState

    object NotFound : SearchState

    object EmptyEditTextInFocus : SearchState
}
