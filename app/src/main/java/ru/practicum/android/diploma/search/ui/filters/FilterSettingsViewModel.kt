package ru.practicum.android.diploma.search.ui.filters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilterSettingsViewModel : ViewModel() {

    private val filterSettingsState = MutableLiveData<FilterState>() // для примера сделал filter state
}
