package ru.practicum.android.diploma.filter.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.filter.domain.api.CountryInteractor
import ru.practicum.android.diploma.filter.domain.api.CountryRepository
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.global.util.RequestResult

class CountryInteractorImpl(
    val countryRepository: CountryRepository
) : CountryInteractor {

    override fun searchIndustries(): Flow<Pair<List<Country>?, Int?>> {
        return countryRepository.searchRegion().map { resource ->
            when (resource) {
                is RequestResult.Success -> Pair(resource.data, null)
                is RequestResult.Error -> Pair(null, 500)
            }
        }
    }
}
