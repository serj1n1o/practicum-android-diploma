package ru.practicum.android.diploma.filter.data

import ru.practicum.android.diploma.filter.data.mapper.FilterMapper
import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.model.FilterStatus
import ru.practicum.android.diploma.global.sharedpreferences.SharedPreferencesFilter

class FilterRepositoryImpl(val sharedPreferencesFilter: SharedPreferencesFilter, val mapper: FilterMapper) :
    FilterRepository {
    private var filterStatus: FilterStatus = FilterStatus(null, null, null, null, false)

    override fun getFilterStatus(): FilterStatus {
        return filterStatus
    }

    override fun setFilterStatus(filterStatus: FilterStatus) {
        val stat = FilterStatus(
            filterStatus.country,
            filterStatus.area,
            filterStatus.industry,
            filterStatus.salary,
            filterStatus.onlyWithSalary
        )
        stat.isFilterActive = filterStatus.isFilterActive
        this.filterStatus = stat
    }

    override fun clearFilters() {
        filterStatus = FilterStatus(null, null, null, null, false)
    }

    override fun saveFilterToSharedPreferences(filterStatus: FilterStatus) {
        sharedPreferencesFilter.saveFilterState(mapper.filterToFilterDto(filterStatus))
    }

    override fun loadFilterFromSharedPreferences(): FilterStatus {
        return mapper.filterDtoToFilter(sharedPreferencesFilter.getFilterState())
    }

}
