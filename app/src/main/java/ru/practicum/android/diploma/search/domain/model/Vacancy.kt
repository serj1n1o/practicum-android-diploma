package ru.practicum.android.diploma.search.domain.model

import com.google.gson.annotations.SerializedName

data class Vacancy(
    val id: String?,
    val name: String?,
    val salary: String?,
    val employer: String?,
    val area: String?,
    val experience: String?,
    val employment: String?,
    val schedule: String?,
    val description: String?,
    @SerializedName("key_skills")
    val keySkills: List<String>?,
    val contacts: String?,
    val address: String?,
    @SerializedName("alternate_url")
    val alternateUrl: String?,
    val isFavorite: Boolean = false,
) {
    fun withFavoriteStatus(isFavorite: Boolean): Vacancy {
        return this.copy(isFavorite = isFavorite)
    }
}
