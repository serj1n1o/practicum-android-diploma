package ru.practicum.android.diploma.global.sharedpreferences.dto

import ru.practicum.android.diploma.search.data.dto.AreaDto
import ru.practicum.android.diploma.search.data.dto.CountryDto
import ru.practicum.android.diploma.search.data.dto.industries.IndustryDto

class FilterStatusDto(
    val country: CountryDto? = null,
    val area: AreaDto? = null,
    val industry: IndustryDto? = null,
    val salary: Int? = null,
    val onlyWithSalary: Boolean = false,
) {
    var isFilterActive: Boolean = false
}
