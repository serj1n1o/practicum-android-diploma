package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.filter.domain.model.Location
import ru.practicum.android.diploma.search.data.dto.industries.IndustriesDto
import ru.practicum.android.diploma.search.data.dto.regions.AreasResponse

class FilterMapper {

    fun mapIndustriesResponseDtoToIndustries(industriesResponseDto: List<IndustriesDto>): List<Industry> {
        val industries = mutableListOf<Industry>()
        industriesResponseDto.forEach { industriesDto ->
            industries.add(
                Industry(
                    industriesDto.id,
                    industriesDto.name
                )
            )
            industriesDto.industries.forEach { industryDto ->
                industries.add(
                    Industry(
                        industryDto.id,
                        industryDto.name
                    )
                )
            }
        }
        return industries
    }

    fun mapAreasResponseCountryToCountries(areaResponse: AreasResponse): List<Country> {
        return areaResponse.map {
            Country(
                it.id,
                it.name
            )
        }
    }

    fun mapToLocations(areasResponse: AreasResponse): List<Location> {
        val locations = mutableListOf<Location>()

        areasResponse.forEach { areaResponseCountry ->
            val country = Country(
                id = areaResponseCountry.id,
                name = areaResponseCountry.name
            )

            areaResponseCountry.areas.forEach { areaRegion ->
                val area = Area(
                    id = areaRegion.id,
                    name = areaRegion.name,
                    parentId = areaRegion.parentId
                )

                locations.add(
                    Location(
                        country = country,
                        area = area
                    )
                )

                areaRegion.areas.forEach { areaRegionChild ->
                    if (areaRegionChild.areas.isEmpty()) {
                        val childArea = Area(
                            id = areaRegionChild.id,
                            name = areaRegionChild.name,
                            parentId = areaRegionChild.parentId
                        )

                        locations.add(
                            Location(
                                country = country,
                                area = childArea
                            )
                        )
                    }
                }
            }
        }

        return locations.sortedBy { it.area.name }
    }


}
