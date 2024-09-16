package ru.practicum.android.diploma.global.sharedpreferences

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.global.sharedpreferences.dto.FilterStatusDto

class SharedPreferencesFilterImpl(
    private val sharedPrefs: SharedPreferences,
    private val gson: Gson
) : SharedPreferencesFilter {
    override fun saveFilterState(filterParam: FilterStatusDto) {
        val json = gson.toJson(filterParam)
        sharedPrefs.edit()
            .putString(FILTER_STATE, json)
            .apply()
    }

    override fun getFilterState(): FilterStatusDto {
        val json =
            sharedPrefs.getString(
                FILTER_STATE, null
            ) ?: return FilterStatusDto()
        return gson.fromJson(json, FilterStatusDto::class.java)
    }

    companion object {
        const val FILTER_STATE = "filter_state"
    }
}
