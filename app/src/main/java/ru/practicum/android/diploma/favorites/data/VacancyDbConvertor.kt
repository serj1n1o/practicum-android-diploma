package ru.practicum.android.diploma.favorites.data

import ru.practicum.android.diploma.global.db.entity._VacancyEntity
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class VacancyDbConvertor {
    fun mapVacancyDetailsToVacancyEntity(vacancy: VacancyDetails): _VacancyEntity {
        var skills: String? = null
        if (vacancy.keySkills?.isNotEmpty() == true) {
            skills = vacancy.keySkills?.joinToString(",")
        }
        return _VacancyEntity(
            id = vacancy.id,
            name = vacancy.name,
            salary = vacancy.salary,
            employerLogo = vacancy.employerLogo,
            employerName = vacancy.employerName,
            area = vacancy.area,
            experience = vacancy.experience,
            employment = vacancy.experience,
            schedule = vacancy.schedule,
            description = vacancy.description,
            keySkills = skills,
            alternateUrl = vacancy.alternateUrl,
            inFavorite = true,
        )
    }

    fun mapVacancyEntityToVacancyDetails(vacancy: _VacancyEntity): VacancyDetails {
        var skills = mutableListOf<String>()
        if (vacancy.keySkills?.isNotEmpty() == true) {
            skills.addAll(vacancy.keySkills.split(",").toMutableList())
        }
        return VacancyDetails(
            id = vacancy.id,
            name = vacancy.name,
            salary = vacancy.salary,
            employerLogo = vacancy.employerLogo,
            employerName = vacancy.employerName,
            area = vacancy.area,
            experience = vacancy.experience,
            employment = vacancy.experience,
            schedule = vacancy.schedule,
            description = vacancy.description,
            keySkills = skills,
            alternateUrl = vacancy.alternateUrl,
            inFavorite = true,
        )
    }

    fun mapVacancyEntityToVacancy(vacancy: _VacancyEntity): Vacancy {
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

    fun mapListVacancyEntityToListVacancy(vacanciesEntity: List<_VacancyEntity>): List<Vacancy> {
        val vacancies = mutableListOf<Vacancy>()
        vacanciesEntity.forEach { vacancy ->
            vacancies.add(mapVacancyEntityToVacancy(vacancy))
        }
        return vacancies
    }
}
