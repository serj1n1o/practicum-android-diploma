package ru.practicum.android.diploma.search.ui

import ru.practicum.android.diploma.search.domain.model.VacancyList

sealed interface SearchState {
    object Loading : SearchState

    object LoadingNewPage : SearchState

    data class Content(val vacancies: VacancyList) : SearchState

    object NoConnection : SearchState

    object EmptyEditText : SearchState

    object NotFound : SearchState

    object EmptyEditTextInFocus : SearchState
}
