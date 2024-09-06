package ru.practicum.android.diploma.search.data.dto.details

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.global.data.network.dto.Response
import ru.practicum.android.diploma.search.data.dto.AddressDto
import ru.practicum.android.diploma.search.data.dto.AreaDto
import ru.practicum.android.diploma.search.data.dto.EmployerDto
import ru.practicum.android.diploma.search.data.dto.SalaryDto

data class VacancyResponse(
    val id: String,
    val name: String,
    val salary: SalaryDto?,
    val employer: EmployerDto?,
    val area: AreaDto,
    val experience: ExperienceDto?,
    val employment: EmploymentDto?,
    val schedule: ScheduleDto?,
    val description: String,
    @SerializedName("key_skills")
    val keySkills: List<KeySkillsDto>?,
    val contacts: ContactsDto?,
    val address: AddressDto?,
    @SerializedName("alternate_url")
    val alternateUrl: String,
) : Response()
