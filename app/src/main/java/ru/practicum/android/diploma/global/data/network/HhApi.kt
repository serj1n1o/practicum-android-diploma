package ru.practicum.android.diploma.global.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.search.data.dto.VacanciesListResponse
import ru.practicum.android.diploma.vacancy.data.dto.VacancyResponse

interface HhApi {

    @GET("/vacancies")
    suspend fun getVacanciesRequest(@QueryMap options: Map<String, String>): VacanciesListResponse

    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancyById(@Path("vacancy_id") id: String): VacancyResponse
}
