package ru.practicum.android.diploma.search.data.dto

class VacancyDto(
    val id: String,
    val name: String,
    val area: AreaDto,
    val salary: SalaryDto?,
    val employer: EmployerDto
)
