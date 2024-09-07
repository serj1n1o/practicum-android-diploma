package ru.practicum.android.diploma.search.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.global.util.RequestResult
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class SearchInteractorImpl(private val repository: SearchRepository) : SearchInteractor {
    override fun getDetailsVacancy(vacancyId: String): Flow<Pair<VacancyDetails?, Int?>> {
        return repository.getVacancy(vacancyId).map { result ->
            when (result) {
                is RequestResult.Success -> Pair(result.data, null)
                is RequestResult.Error -> Pair(null, result.error)
            }
        }
    }
}
