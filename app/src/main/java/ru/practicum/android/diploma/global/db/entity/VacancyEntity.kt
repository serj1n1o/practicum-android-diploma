package ru.practicum.android.diploma.global.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy_table")
data class VacancyEntity(
    @PrimaryKey val id: String,
    val name: String,
    val salary: String?,
    val employerLogo: String?,
    val employerName: String?,
    val area: String,
    val experience: String?,
    val employment: String?,
    val schedule: String?,
    val description: String,
    val keySkills: String?,
    val alternateUrl: String,
    val inFavorite: Boolean,
)
