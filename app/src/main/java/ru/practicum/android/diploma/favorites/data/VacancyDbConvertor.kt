package ru.practicum.android.diploma.favorites.data

import ru.practicum.android.diploma.global.db.entity.VacancyEntity
import ru.practicum.android.diploma.global.db.entity._VacancyEntity
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class VacancyDbConvertor {
    fun map(vacancy: VacancyDetails): _VacancyEntity {
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
}
