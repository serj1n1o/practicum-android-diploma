package ru.practicum.android.diploma.filter.data

import ru.practicum.android.diploma.filter.data.mapper.FilterMapper
import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.models.Area
import ru.practicum.android.diploma.filter.domain.models.City
import ru.practicum.android.diploma.filter.domain.models.Industry
import ru.practicum.android.diploma.filter.domain.models.Salary
import ru.practicum.android.diploma.search.data.dto.AreaDto
import ru.practicum.android.diploma.search.data.dto.CityDto
import ru.practicum.android.diploma.search.data.dto.SalaryDto
import ru.practicum.android.diploma.search.data.dto.industries.IndustryDto

class FilterRepositoryImpl(
    private val filterMapper: FilterMapper
) : FilterRepository {
    private var cityDto: CityDto? = null
    private var areaDto: AreaDto? = null
    private var salaryDto: SalaryDto? = null
    private var industriesDto = mutableListOf<IndustryDto>()

    override fun getCity(): City? {
        return filterMapper.mapCityDtoToCity(cityDto)
    }

    override fun setCity(city: City?) {
        cityDto = filterMapper.mapCityToCityDto(city)
    }

    override fun getArea(): Area? {
        return filterMapper.mapAreaDtoToArea(areaDto)
    }

    override fun setArea(area: Area?) {
        areaDto = filterMapper.mapAreaToAreaDto(area)
    }

    override fun getSalary(): Salary? {
        return filterMapper.mapSalaryDtoToSalary(salaryDto)
    }

    override fun setSalary(salary: Salary?) {
        salaryDto = filterMapper.mapSalaryToSalaryDto(salary)
    }

    override fun getIndustries(): MutableList<Industry> {
        return filterMapper.mapIndustriesDtoToIndustries(industriesDto)
    }

    override fun setIndustries(industries: MutableList<Industry>) {
        industriesDto = filterMapper.mapIndustriesToIndustriesDto(industries)
    }

    override fun clearFilters() {
        cityDto = null
        areaDto = null
        salaryDto = null
        industriesDto = mutableListOf<IndustryDto>()
    }

}
