package ru.practicum.android.diploma.search.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class SearchInteractorImpl : SearchInteractor {
    override fun getDetailsVacancy(vacancyId: String): Flow<Pair<VacancyDetails?, Int?>> {
        TODO("Not yet implemented")
    }
}
