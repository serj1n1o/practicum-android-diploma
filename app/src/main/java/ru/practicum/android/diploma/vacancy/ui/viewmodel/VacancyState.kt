package ru.practicum.android.diploma.vacancy.ui.viewmodel

import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

interface VacancyState {
    data object Loading : VacancyState
    data class Content(val vacancy: VacancyDetails) : VacancyState
    data class Error(val errorCode: Int) : VacancyState
}
