package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry

class FilterInteractorImpl(private val repository: FilterRepository) : FilterInteractor {
    override fun getCity(): Country? {
        return repository.getCountry()
    }

    override fun setCity(country: Country?) {
        repository.setCountry(country)
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

    override fun setOnlyWithSalary(status: Boolean){
        repository.setOnlyWithSalary(status)
    }

    override fun clearFilters() {
        repository.clearFilters()
    }
}
