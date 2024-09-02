package ru.practicum.android.diploma.global.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.search.data.dto.VacanciesListResponse
import ru.practicum.android.diploma.vacancy.data.dto.VacancyResponse

interface HhApi {
    @Headers(BEARER_TOKEN, USER_AGENT_STRING)
    @GET("/vacancies")
    suspend fun getVacanciesRequest(@QueryMap options: Map<String, String>): VacanciesListResponse

    @Headers(BEARER_TOKEN, USER_AGENT_STRING)
    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancyById(@Path("vacancy_id") id: String): VacancyResponse

    companion object {
        private const val USER_AGENT_STRING = "HH-User-Agent: Offers (gabov@list.ru)"
        private const val BEARER_TOKEN = "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    }
}
