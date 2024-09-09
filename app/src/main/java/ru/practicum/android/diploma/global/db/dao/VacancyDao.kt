package ru.practicum.android.diploma.global.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.global.db.entity.VacancyEntity

@Dao
interface VacancyDao {
    @Insert(entity = VacancyEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancyEntity: VacancyEntity)

    @Query("DELETE FROM vacancy_table WHERE id = :vacancyId")
    suspend fun deleteVacancy(vacancyId: String)

    @Query("SELECT * FROM vacancy_table")
    suspend fun getAllVacancies(): List<VacancyEntity>

    @Query("SELECT * FROM vacancy_table WHERE id = :vacancyId")
    suspend fun getFavoriteVacancy(vacancyId: String): VacancyEntity

    @Query("SELECT id FROM vacancy_table")
    suspend fun getIdsFavoriteVacancies(): List<String>
}
