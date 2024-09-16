package ru.practicum.android.diploma.search.domain.model

data class Vacancy(
    val id: String,
    val name: String,
    val salary: String?,
    val employer: String?,
    val area: String,
    val logo: String?,
)
