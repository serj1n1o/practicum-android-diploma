package ru.practicum.android.diploma.search.data.dto.details

data class ContactsDto(
    val email: String?,
    val name: String?,
    val phones: List<PhonesDto>?
)
