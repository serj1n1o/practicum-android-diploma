package ru.practicum.android.diploma.global.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.practicum.android.diploma.global.db.entity.VacancyEntity

@Dao
interface VacancyDao {
    @Insert(entity = VacancyEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancyEntity: VacancyEntity)

    @Query("SELECT * FROM vacancy_table WHERE id = :vacancyId")
    suspend fun getVacancy(vacancyId: String): VacancyEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVacancy(vacancy: VacancyEntity)

    @Query("DELETE FROM vacancy_table WHERE id = :vacancyId")
    suspend fun deleteVacancy(vacancyId: String)

    @Query("SELECT * FROM vacancy_table")
    suspend fun getAllVacancies(): List<VacancyEntity>

    @Query("SELECT id FROM vacancy_table")
    suspend fun getIdsVacancies(): List<String>
}
