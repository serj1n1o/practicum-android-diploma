package ru.practicum.android.diploma.favorites.data

import ru.practicum.android.diploma.global.db.entity.VacancyEntity
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class VacancyDbConvertor {
    fun mapVacancyDetailsToVacancyEntity(vacancy: VacancyDetails): VacancyEntity {
        var skills: String? = null
        if (vacancy.keySkills?.isNotEmpty() == true) {
            skills = vacancy.keySkills?.joinToString(",")
        }
        with(vacancy) {
            return VacancyEntity(
                id = id,
                name = name,
                salary = salary,
                employerLogo = employerLogo,
                employerName = employerName,
                area = area,
                experience = experience,
                employment = experience,
                schedule = schedule,
                description = description,
                keySkills = skills,
                alternateUrl = alternateUrl,
                inFavorite = true,
            )
        }
    }

    fun mapVacancyEntityToVacancyDetails(vacancy: VacancyEntity): VacancyDetails {
        val skills = mutableListOf<String>()
        if (vacancy.keySkills?.isNotEmpty() == true) {
            skills.addAll(vacancy.keySkills.split(",").toMutableList())
        }
        with(vacancy) {
            return VacancyDetails(
                id = id,
                name = name,
                salary = salary,
                employerLogo = employerLogo,
                employerName = employerName,
                area = area,
                experience = experience,
                employment = experience,
                schedule = schedule,
                description = description,
                keySkills = skills,
                alternateUrl = alternateUrl,
                inFavorite = true,
            )
        }
    }

    fun mapVacancyEntityToVacancy(vacancy: VacancyEntity): Vacancy {
        with(vacancy) {
            return Vacancy(
                id = id,
                name = name,
                salary = salary,
                employer = employerName,
                area = area,
                logo = employerLogo,
            )
        }
    }

    fun mapListVacancyEntityToListVacancy(vacanciesEntity: List<VacancyEntity>): List<Vacancy> {
        val vacancies = mutableListOf<Vacancy>()
        vacanciesEntity.forEach { vacancy ->
            vacancies.add(mapVacancyEntityToVacancy(vacancy))
        }
        return vacancies
    }
}
