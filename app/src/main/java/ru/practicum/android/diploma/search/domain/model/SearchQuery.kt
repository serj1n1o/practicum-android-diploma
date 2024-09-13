package ru.practicum.android.diploma.search.domain.model

class SearchQuery(
    var page: Int,
    var text: String? = null,
    val perPage: Int = 20,
    val areaId: String? = null,
    val industryId: String? = null,
    val salary: Int? = null,
    val onlyWithSalary: Boolean? = null,
)
