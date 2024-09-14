package ru.practicum.android.diploma.search.data.dto.regions

import com.google.gson.annotations.SerializedName

data class AreaRegions(
    val areas: List<AreaRegionChild>,
    val id: String,
    val name: String,
    @SerializedName("parent_id")
    val parentId: String,
)
