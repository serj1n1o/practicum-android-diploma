package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.model.FilterStatus

interface FilterRepository {
    fun getFilterStatus(): FilterStatus
    fun setFilterStatus(filterStatus: FilterStatus)

    fun clearFilters()

    fun saveFilterToSharedPreferences(filterStatus: FilterStatus)
    fun loadFilterFromSharedPreferences(): FilterStatus
}
