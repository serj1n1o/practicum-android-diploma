package ru.practicum.android.diploma.filter.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.filter.domain.api.CountryRepository
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.global.data.network.NetworkClient
import ru.practicum.android.diploma.global.data.network.dto.Request
import ru.practicum.android.diploma.global.util.RequestResult

class CountryRepositoryImpl(
    val networkClient: NetworkClient
) : CountryRepository {

    override fun searchRegion(): Flow<RequestResult<List<Country>>> = flow {
        val response = networkClient.doRequest(Request.GetAreas)
        TODO("Доработать загрузку стран для фильтра")
    }
}
