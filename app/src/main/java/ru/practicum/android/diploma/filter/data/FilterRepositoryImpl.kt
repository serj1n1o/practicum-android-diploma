package ru.practicum.android.diploma.filter.data

import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry

class FilterRepositoryImpl : FilterRepository {
    private var country: Country? = null
    private var area: Area? = null
    private var salary: Int? = null
    private var onlyWithSalary: Boolean = false
    private var industries = mutableListOf<Industry>()

    override fun getCountry(): Country? {
        return country
    }

    override fun setCountry(country: Country?) {
        this.country = country
    }

    override fun getArea(): Area? {
        return area
    }

    override fun setArea(area: Area?) {
        this.area = area
    }

    override fun getSalary(): Int? {
        return salary
    }

    override fun setSalary(salary: Int?) {
        this.salary = salary
    }

    override fun getIndustries(): MutableList<Industry> {
        return industries
    }

    override fun setIndustries(industries: MutableList<Industry>) {
        this.industries = industries
    }

    override fun setOnlyWithSalary(status: Boolean) {
        onlyWithSalary = status
    }

    override fun clearFilters() {
        country = null
        area = null
        salary = null
        industries = mutableListOf<Industry>()
    }

}
