package ru.practicum.android.diploma.search.data

import ru.practicum.android.diploma.global.util.Mapper.mapSalaryToString
import ru.practicum.android.diploma.global.util.Mapper.mapSkillsToStringList
import ru.practicum.android.diploma.search.data.dto.details.VacancyResponse
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class VacancyConverter {

    fun mapVacancyResponseToVacancyDetails(vacancy: VacancyResponse, inFavorite: Boolean): VacancyDetails {
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
