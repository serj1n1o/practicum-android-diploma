package ru.practicum.android.diploma.filter.ui.mainfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.model.FilterStatus

class FilterSettingsViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {

    private val filterSettingsState = MutableLiveData<FilterState>()
    fun getFilterState(): LiveData<FilterState> = filterSettingsState

    private val checkChangedState = MutableLiveData<Boolean>()
    fun getCheckChangedState(): LiveData<Boolean> = checkChangedState

    private var state: FilterStatus? = null
    private var newState: FilterStatus? = null

    fun setNewState(changedState: FilterState) {
        newState = when (changedState) {
            is FilterState.Content -> changedState.filterStatus
            FilterState.Empty -> FilterStatus(
                country = null,
                area = null,
                industry = null,
                salary = null,
                onlyWithSalary = false
            )
        }
        checkChangedState.value = state != newState
    }

    private fun resetChangeState() {
        checkChangedState.value = false
        state = FilterStatus(
            country = null,
            area = null,
            industry = null,
            salary = null,
            onlyWithSalary = false
        )
    }

    fun setSalary(salary: Int?) {
        val status = filterInteractor.getFilterState()
        val filterState = FilterStatus(
            salary = salary,
            industry = status.industry,
            country = status.country,
            area = status.area,
            onlyWithSalary = status.onlyWithSalary
        )
        filterInteractor.setFilterState(filterState)
        filterSettingsState.postValue(FilterState.Content(filterState))
    }

    fun setOnlyWithSalary(onlySalary: Boolean) {
        val status = filterInteractor.getFilterState()
        val filterState = FilterStatus(
            salary = status.salary,
            industry = status.industry,
            country = status.country,
            area = status.area,
            onlyWithSalary = onlySalary
        )
        filterInteractor.setFilterState(filterState)
        filterSettingsState.postValue(FilterState.Content(filterState))
    }

    fun getStateAreaAndIndustry() {
        val data = filterInteractor.getFilterState()
        if (!data.isDefaultParams()) {
            filterSettingsState.postValue(FilterState.Content(data))
        }
    }

    fun getSettingsFilter() {
        val prefs = filterInteractor.loadFilterFromSharedPreferences()
        filterInteractor.setFilterState(prefs)
        filterSettingsState.postValue(
            FilterState.Content(prefs)
        )
        state = prefs
    }

    fun saveSettingsFilter() {
        filterInteractor.saveFilterToSharedPreferences(filterInteractor.getFilterState())
    }

    fun resetSettings() {
        filterSettingsState.postValue(
            FilterState.Empty
        )
        resetChangeState()
        filterInteractor.clearFilters()
        filterInteractor.saveFilterToSharedPreferences(filterInteractor.getFilterState())
    }

    fun resetPlaceWorkFilter() {
        val status = filterInteractor.getFilterState()
        val filterState = FilterStatus(
            salary = status.salary,
            industry = status.industry,
            country = null,
            area = null,
            onlyWithSalary = status.onlyWithSalary
        )
        filterInteractor.setFilterState(filterState)
        filterSettingsState.postValue(FilterState.Content(filterState))
    }

    fun resetIndustryFilter() {
        val status = filterInteractor.getFilterState()
        val filterState = FilterStatus(
            salary = status.salary,
            industry = null,
            country = status.country,
            area = status.area,
            onlyWithSalary = status.onlyWithSalary
        )
        filterInteractor.setFilterState(filterState)
        filterSettingsState.postValue(FilterState.Content(filterState))
    }
}
