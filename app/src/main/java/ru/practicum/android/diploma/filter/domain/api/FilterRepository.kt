package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry

interface FilterRepository {
    fun getCountry(): Country?
    fun setCountry(country: Country?)

    fun getArea(): Area?
    fun setArea(area: Area?)

    fun getSalary(): Int?
    fun setSalary(salary: Int?)

    fun getIndustries(): MutableList<Industry>
    fun setIndustries(industries: MutableList<Industry>)

    fun setOnlyWithSalary(status: Boolean)

    fun clearFilters()
}
