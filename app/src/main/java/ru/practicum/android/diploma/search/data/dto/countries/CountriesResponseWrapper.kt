package ru.practicum.android.diploma.search.data.dto.countries

import ru.practicum.android.diploma.global.data.network.dto.Response

data class CountriesResponseWrapper(
    val countriesResponse: CountriesResponse,
) : Response()
