package ru.practicum.android.diploma.search.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy_table")
data class VacancyEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val area: String?,
    val link: String?,
    val address: String?,
    val contactName: String?,
    val contactPhone: String?,
    val contactEmail: String?,
    val employerName: String?,
    val salary: String?,
    val schedule: String?,
    val skills: String?,
    val experience: String?,
    val description: String?,
    val isFavorite: Boolean
)
