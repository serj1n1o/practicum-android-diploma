package ru.practicum.android.diploma.search.data.dto.regions

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.global.data.network.dto.Response

data class AreasResponseCountry(
    val areas: List<AreaRegions>,
    val id: String,
    val name: String,
    @SerializedName("parent_id")
    val parentId: Any,
) : Response()
