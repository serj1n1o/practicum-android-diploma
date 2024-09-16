package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.data.mapper.FilterMapper
import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.model.FilterStatus
import ru.practicum.android.diploma.global.sharedpreferences.SharedPreferencesFilter

class FilterInteractorImpl(
    private val repository: FilterRepository,
    private val sharedPreferencesFilter: SharedPreferencesFilter,
    private val mapper: FilterMapper
) : FilterInteractor {

    override fun getFilterState(): FilterStatus {
        return repository.getFilterStatus()
    }

    override fun setFilterState(filterState: FilterStatus) {
        repository.setFilterStatus(filterState)
    }

    override fun clearFilters() {
        repository.clearFilters()
    }

    override fun saveFilterToSharedPreferences(filterStatus: FilterStatus) {
        sharedPreferencesFilter.saveFilterState(mapper.filterToFilterDto(filterStatus))
    }

    override fun loadFilterFromSharedPreferences(): FilterStatus {
        return mapper.filterDtoToFilter(sharedPreferencesFilter.getFilterState())
    }
}
