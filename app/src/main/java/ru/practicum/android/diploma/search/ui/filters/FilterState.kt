package ru.practicum.android.diploma.search.ui.filters

sealed interface FilterState {
    data class Content(
        val country: String?,
        val city: String?,
        val industry: String?,
        val salary: Int?,
        val onlyWithSalary: Boolean?,
    ) : FilterState

    data object Empty : FilterState
}
