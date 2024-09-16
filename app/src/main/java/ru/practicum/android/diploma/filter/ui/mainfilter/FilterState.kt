package ru.practicum.android.diploma.filter.ui.mainfilter

sealed interface FilterState {
    data class Content(
        val country: String? = null,
        val city: String? = null,
        val industry: String? = null,
        val salary: Int? = null,
        val onlyWithSalary: Boolean? = null,
    ) : FilterState

    data object Empty : FilterState
}
