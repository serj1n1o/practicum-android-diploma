package ru.practicum.android.diploma.global.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.search.data.dto.VacanciesListResponse
import ru.practicum.android.diploma.search.data.dto.details.VacancyResponse
import ru.practicum.android.diploma.search.data.dto.industries.IndustriesResponseDto
import ru.practicum.android.diploma.search.data.dto.regions.AreasResponse

interface HhApi {

    @GET("/vacancies")
    suspend fun getVacanciesRequest(@QueryMap options: Map<String, String>): VacanciesListResponse

    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancyById(@Path("vacancy_id") id: String): VacancyResponse

    @GET("/areas")
    suspend fun getAreas(): AreasResponse

    @GET("/industries")
    suspend fun getIndustries(): IndustriesResponseDto
}
