package ru.practicum.android.diploma.filter.data.mapper

import ru.practicum.android.diploma.filter.domain.models.Area
import ru.practicum.android.diploma.filter.domain.models.City
import ru.practicum.android.diploma.filter.domain.models.Industry
import ru.practicum.android.diploma.filter.domain.models.Salary
import ru.practicum.android.diploma.search.data.dto.AreaDto
import ru.practicum.android.diploma.search.data.dto.CityDto
import ru.practicum.android.diploma.search.data.dto.SalaryDto
import ru.practicum.android.diploma.search.data.dto.industries.IndustryDto

class FilterMapper {
    fun mapCityDtoToCity(cityDto: CityDto?): City? {
        if (cityDto == null) return null
        return City(id = cityDto.id, name = cityDto.name)
    }

    fun mapCityToCityDto(city: City?): CityDto? {
        if (city == null) return null
        return CityDto(id = city.id, name = city.name)
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
            val industy = Industry(
                id = industry.id,
                name = industry.name
            )
            industries.add(industy)
        }
        return industries
    }

    fun mapIndustriesToIndustriesDto(industries: MutableList<Industry>): MutableList<IndustryDto> {
        val industriesDto = mutableListOf<IndustryDto>()
        for (industry in industries) {
            val industyDto = IndustryDto(
                id = industry.id,
                name = industry.name
            )
            industriesDto.add(industyDto)
        }
        return industriesDto
    }
}
