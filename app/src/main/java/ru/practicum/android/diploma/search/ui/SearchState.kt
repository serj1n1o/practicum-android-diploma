package ru.practicum.android.diploma.search.ui

sealed interface SearchState {
    object Loading : SearchState

    data class Content(val vacancies: List<SearchViewModel._Vacancy>) : SearchState

    object NoConnection : SearchState

    object EmptyEditText : SearchState

    object NotFound : SearchState

    object Empty: SearchState
}
