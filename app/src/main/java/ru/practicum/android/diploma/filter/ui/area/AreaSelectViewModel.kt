package ru.practicum.android.diploma.filter.ui.area

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.model.FilterStatus
import ru.practicum.android.diploma.filter.domain.model.Location
import ru.practicum.android.diploma.global.util.debounce

class AreaSelectViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {
    private val stateLiveData = MutableLiveData<AreaSelectState>()
    private var lastSearchText: String? = null
    var locations = mutableListOf<Location>()
    fun observeState(): LiveData<AreaSelectState> = stateLiveData

    private val trackSearchDebounce =
        debounce<String>(FILTER_DEBOUNCE_DELAY, viewModelScope, true) { changedText ->
            filter(changedText)
        }

    init {
        loadArea()
    }

    fun filterDebounce(changedText: String) {
        if (lastSearchText != changedText) {
            lastSearchText = changedText
            trackSearchDebounce(changedText)
        }
    }

    fun loadArea() {
        // Тут нужна загрузка областей в locations из filter репозитория или если там пусто то из сети

        if (locations.size == 0) {
            renderState(AreaSelectState.Error)
        } else {
            renderState(AreaSelectState.Content(locations))
        }
    }

    fun filter(text: String) {
        val filteredList = mutableListOf<Location>()
        if (text.isEmpty()) {
            filteredList.addAll(locations)
        } else {
            for (item in locations) {
                if (item.area.name?.contains(text, ignoreCase = true) == true) {
                    filteredList.add(item)
                }
            }
        }
        if (filteredList.size > 0) {
            renderState(AreaSelectState.Content(filteredList))
        } else {
            renderState(AreaSelectState.NotFound)
        }
    }

    fun saveSelection(location: Location) {
        var state = filterInteractor.getFilterState()
        val filterStatus =
            FilterStatus(location.country, location.area, state.industry, state.salary, state.onlyWithSalary)

        filterInteractor.setFilterState(filterStatus)
    }

    private fun renderState(state: AreaSelectState) {
        stateLiveData.postValue(state)
    }

    companion object {
        private const val FILTER_DEBOUNCE_DELAY = 2000L
    }
}
