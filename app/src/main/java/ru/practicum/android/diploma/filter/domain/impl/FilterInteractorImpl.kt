package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry

class FilterInteractorImpl(private val repository: FilterRepository) : FilterInteractor {
    override fun getCountry(): Country? {
        return repository.getCountry()
    }

    override fun setCountry(country: Country?) {
        repository.setCountry(country)
    }

    override fun getArea(): Area? {
        return repository.getArea()
    }

    override fun setArea(area: Area?) {
        repository.setArea(area)
    }

    override fun getIndustry(): Industry? {
        return repository.getIndustry()
    }

    override fun setIndustry(industry: Industry?) {
        repository.setIndustry(industry)
    }

    override fun setOnlyWithSalary(status: Boolean) {
        repository.setOnlyWithSalary(status)
    }

    override fun clearFilters() {
        repository.clearFilters()
    }
}
