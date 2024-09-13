package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.models.Area
import ru.practicum.android.diploma.filter.domain.models.City
import ru.practicum.android.diploma.filter.domain.models.Industry

class FilterInteractorImpl(private val repository: FilterRepository) : FilterInteractor {
    override fun getCity(): City? {
        return repository.getCity()
    }

    override fun setCity(city: City?) {
        repository.setCity(city)
    }

    override fun getArea(): Area? {
        return repository.getArea()
    }

    override fun setArea(area: Area?) {
        repository.setArea(area)
    }

    override fun getIndustries(): MutableList<Industry> {
        return repository.getIndustries()
    }

    override fun setIndustries(industries: MutableList<Industry>) {
        repository.setIndustries(industries)
    }

    override fun clearFilters() {
        repository.clearFilters()
    }
}
