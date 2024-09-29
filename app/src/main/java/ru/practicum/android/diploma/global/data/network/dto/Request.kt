package ru.practicum.android.diploma.global.data.network.dto

sealed interface Request {
    data class GetVacancies(
        val params: Map<String, String>
    ) : Request

    data class GetVacancyById(
        val vacancyId: String
    ) : Request

    data object GetAreas : Request

    data object GetIndustries : Request

    data object GetCountries : Request
}
