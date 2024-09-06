package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

interface SearchInteractor {

    fun getDetailsVacancy(vacancyId: String): Flow<Pair<VacancyDetails?, Int?>>
}
