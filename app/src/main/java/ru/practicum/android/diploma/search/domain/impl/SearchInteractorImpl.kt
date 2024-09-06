package ru.practicum.android.diploma.search.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class SearchInteractorImpl(private val repository: SearchRepository) : SearchInteractor {
    override fun getDetailsVacancy(vacancyId: String): Flow<Pair<VacancyDetails?, Int?>> {
        TODO("Not yet implemented")
    }
}
