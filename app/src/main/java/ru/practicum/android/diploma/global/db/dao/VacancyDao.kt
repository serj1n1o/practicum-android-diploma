package ru.practicum.android.diploma.global.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.global.db.entity.VacancyEntity
import ru.practicum.android.diploma.global.db.entity._VacancyEntity
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

@Dao
interface VacancyDao {
    @Insert(entity = _VacancyEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancyEntity: _VacancyEntity)

    @Query("DELETE FROM _vacancy_table WHERE id = :vacancyId")
    suspend fun deleteVacancy(vacancyId: String)

    @Query("SELECT * FROM _vacancy_table")
    suspend fun getAllVacancies(): List<_VacancyEntity>

    @Query("SELECT * FROM _vacancy_table WHERE id = :vacancyId")
    suspend fun getFavoriteVacancy(vacancyId: String): _VacancyEntity

    @Query("SELECT id FROM _vacancy_table")
    suspend fun getIdsFavoriteVacancies(): List<String>
}
