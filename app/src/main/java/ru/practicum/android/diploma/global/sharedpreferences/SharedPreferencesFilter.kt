package ru.practicum.android.diploma.global.sharedpreferences

import ru.practicum.android.diploma.global.sharedpreferences.dto.FilterStatusDto

interface SharedPreferencesFilter {
    fun saveFilterState(filterParam: FilterStatusDto)
    fun getFilterState(): FilterStatusDto
}
