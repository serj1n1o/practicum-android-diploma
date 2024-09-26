package ru.practicum.android.diploma.filter.domain.model

class FilterStatus(
    val country: Country?,
    val area: Area?,
    val industry: Industry?,
    val salary: Int?,
    val onlyWithSalary: Boolean = false,
) {
    var isFilterActive: Boolean = false

    fun isDefaultParams(): Boolean {
        return country == null && area == null && industry == null && salary == null && !onlyWithSalary
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FilterStatus

        if (country != other.country) return false
        if (area != other.area) return false
        if (industry != other.industry) return false
        if (salary != other.salary) return false
        if (onlyWithSalary != other.onlyWithSalary) return false
        if (isFilterActive != other.isFilterActive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = country?.hashCode() ?: 0
        result = 31 * result + (area?.hashCode() ?: 0)
        result = 31 * result + (industry?.hashCode() ?: 0)
        result = 31 * result + (salary ?: 0)
        result = 31 * result + onlyWithSalary.hashCode()
        result = 31 * result + isFilterActive.hashCode()
        return result
    }
}
