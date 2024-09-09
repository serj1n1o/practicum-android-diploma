package ru.practicum.android.diploma.vacancy.domain.model

data class VacancyDetails(
    val id: String,
    val name: String,
    val salary: String?,
    val employerLogo: String?,
    val employerName: String?,
    val area: String,
    val experience: String?,
    val employment: String?,
    val schedule: String?,
    val description: String,
    val keySkills: List<String>?,
    val alternateUrl: String,
    val inFavorite: Boolean,
)
