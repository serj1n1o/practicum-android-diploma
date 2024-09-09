package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.global.util.Mapper.mapSalaryToString
import ru.practicum.android.diploma.global.util.Mapper.mapSkillsToStringList
import ru.practicum.android.diploma.search.data.dto.VacanciesListResponse
import ru.practicum.android.diploma.search.data.dto.VacancyDto
import ru.practicum.android.diploma.search.data.dto.details.VacancyResponse
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.domain.model.VacancyList
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class VacancyMapper {
    fun map(vacanciesDto: VacanciesListResponse) = VacancyList(
        found = vacanciesDto.found,
        items = map(vacanciesDto.items),
        page = vacanciesDto.page,
        pages = vacanciesDto.pages,
        perPage = vacanciesDto.perPage
    )

    private fun map(listDto: List<VacancyDto>): List<Vacancy> {
        val vacancyList = mutableListOf<Vacancy>()
        for (vacancyDto in listDto) {
            val vacancy = Vacancy(
                id = vacancyDto.id,
                name = vacancyDto.name,
                area = vacancyDto.area.name,
                salary = mapSalaryToString(vacancyDto.salary),
                employer = vacancyDto.employer.name,
                logo = vacancyDto.employer.logoUrls?.logoUrl90
            )
            vacancyList.add(vacancy)
        }
        return vacancyList
    }

    fun map(vacancy: VacancyResponse, inFavorite: Boolean): VacancyDetails {
        return with(vacancy) {
            VacancyDetails(
                id = id,
                name = name,
                salary = mapSalaryToString(salary),
                employerLogo = employer?.logoUrls?.logoUrl90,
                employerName = employer?.name,
                area = area.name,
                experience = experience?.name,
                employment = employment?.name,
                schedule = schedule?.name,
                description = description,
                keySkills = mapSkillsToStringList(keySkills),
                alternateUrl = alternateUrl,
                inFavorite = inFavorite,
            )
        }
    }
}
