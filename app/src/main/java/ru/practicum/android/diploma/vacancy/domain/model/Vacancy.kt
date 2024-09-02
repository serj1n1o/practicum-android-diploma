package ru.practicum.android.diploma.vacancy.domain.model

import java.time.ZonedDateTime

data class Vacancy(
    val id: String,
    val name: String,
    val company: String,
    val location: String,
    val salary: Salary?,
    val description: String,
    val experience: List<String>,
    val employment: List<String>,
    val schedule: List<String>,
    val area: String,
    val metro: List<String>,
    val professionalRoles: List<String>,
    val industry: List<String>,
    val skills: List<String>,
    val publishedAt: ZonedDateTime,
    var isFavorite: Boolean = false,
)

data class Salary(
    val currency: String,
    val from: Int?,
    val to: Int?,
)
