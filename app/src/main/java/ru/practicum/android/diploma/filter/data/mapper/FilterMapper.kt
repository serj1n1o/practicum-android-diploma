package ru.practicum.android.diploma.filter.data.mapper

import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.FilterStatus
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.filter.domain.model.Salary
import ru.practicum.android.diploma.global.sharedpreferences.dto.FilterStatusDto
import ru.practicum.android.diploma.search.data.dto.AreaDto
import ru.practicum.android.diploma.search.data.dto.CountryDto
import ru.practicum.android.diploma.search.data.dto.SalaryDto
import ru.practicum.android.diploma.search.data.dto.industries.IndustryDto

class FilterMapper {
    fun mapCityDtoToCity(countryDto: CountryDto?): Country? {
        if (countryDto == null) return null
        return Country(id = countryDto.id, name = countryDto.name)
    }

    fun mapCityToCityDto(city: Country?): CountryDto? {
        if (city == null) return null
        return CountryDto(id = city.id, name = city.name)
    }

    fun mapAreaDtoToArea(areaDto: AreaDto?): Area? {
        if (areaDto == null) return null
        return Area(id = areaDto.id, name = areaDto.name)
    }

    fun mapAreaToAreaDto(area: Area?): AreaDto? {
        if (area == null) return null
        return AreaDto(id = area.id, name = area.name)
    }

    fun mapSalaryDtoToSalary(salaryDto: SalaryDto?): Salary? {
        if (salaryDto == null) return null
        return Salary(currency = salaryDto.currency, from = salaryDto.from, to = salaryDto.to)
    }

    fun mapSalaryToSalaryDto(salary: Salary?): SalaryDto? {
        if (salary == null) return null
        return SalaryDto(currency = salary.currency, from = salary.from, to = salary.to)
    }

    fun mapIndustryDtoToIndustry(industryDto: IndustryDto?): Industry? {
        if (industryDto == null) return null
        val industry = Industry(
            id = industryDto.id,
            name = industryDto.name
        )
        return industry
    }

    fun mapIndustryToIndustryDto(industry: Industry?): IndustryDto? {
        if (industry == null) return null
        val industryDto = IndustryDto(
            id = industry.id,
            name = industry.name
        )
        return industryDto
    }

    fun filterToFilterDto(filterStatus: FilterStatus): FilterStatusDto {
        val filterStatusDto = FilterStatusDto(
            mapCityToCityDto(filterStatus.country),
            mapAreaToAreaDto(filterStatus.area),
            mapIndustryToIndustryDto(filterStatus.industry),
            filterStatus.salary,
            filterStatus.onlyWithSalary
        )
        filterStatusDto.isFilterActive = filterStatus.isFilterActive
        return filterStatusDto
    }

    fun filterDtoToFilter(filterStatusDto: FilterStatusDto): FilterStatus {
        val filterStatus = FilterStatus(
            mapCityDtoToCity(filterStatusDto.country),
            mapAreaDtoToArea(filterStatusDto.area),
            mapIndustryDtoToIndustry(filterStatusDto.industry),
            filterStatusDto.salary,
            filterStatusDto.onlyWithSalary
        )
        filterStatus.isFilterActive = filterStatusDto.isFilterActive
        return filterStatus
    }
}
