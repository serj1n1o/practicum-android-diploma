package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.models.Area
import ru.practicum.android.diploma.filter.domain.models.City
import ru.practicum.android.diploma.filter.domain.models.Industry

interface FilterInteractor {
    fun getCity(): City?
    fun setCity(city: City?)

    fun getArea(): Area?
    fun setArea(area: Area?)

    fun getIndustries(): MutableList<Industry>
    fun setIndustries(industries: MutableList<Industry>)

    fun clearFilters()
}
