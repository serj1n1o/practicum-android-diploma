package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.models.Area
import ru.practicum.android.diploma.filter.domain.models.City
import ru.practicum.android.diploma.filter.domain.models.Industry
import ru.practicum.android.diploma.filter.domain.models.Salary

interface FilterRepository {
    fun getCity(): City?
    fun setCity(city: City?)

    fun getArea(): Area?
    fun setArea(area: Area?)

    fun getSalary(): Salary?
    fun setSalary(salary: Salary?)

    fun getIndustries(): MutableList<Industry>
    fun setIndustries(industries: MutableList<Industry>)

    fun clearFilters()
}
