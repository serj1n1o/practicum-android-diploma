package ru.practicum.android.diploma.filter.data.mapper

import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.filter.domain.model.Salary
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

    fun mapIndustriesDtoToIndustries(industriesDto: MutableList<IndustryDto>): MutableList<Industry> {
        val industries = mutableListOf<Industry>()
        for (industry in industriesDto) {
            val industry = Industry(
                id = industry.id,
                name = industry.name
            )
            industries.add(industry)
        }
        return industries
    }

    fun mapIndustriesToIndustriesDto(industries: MutableList<Industry>): MutableList<IndustryDto> {
        val industriesDto = mutableListOf<IndustryDto>()
        for (industry in industries) {
            val industryDto = IndustryDto(
                id = industry.id,
                name = industry.name
            )
            industriesDto.add(industryDto)
        }
        return industriesDto
    }
}
