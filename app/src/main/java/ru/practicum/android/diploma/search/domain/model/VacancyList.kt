package ru.practicum.android.diploma.search.domain.model

data class VacancyList(
    val found: Int,
    val items: List<Vacancy>,
    val page: Int,
    val pages: Int,
    val perPage: Int
)
