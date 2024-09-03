package ru.practicum.android.diploma.search.data.dto

import com.google.gson.annotations.SerializedName

data class LogoUrlsDto(
    @SerializedName("90")
    val logoUrl90: String,

    @SerializedName("240")
    val logoUrl240: String
)
