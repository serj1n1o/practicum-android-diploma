package ru.practicum.android.diploma.filter.ui.mainfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.model.FilterStatus

class FilterSettingsViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {

    private val filterSettingsState = MutableLiveData<FilterState>() // для примера сделал filter state

    fun getFilterState(): LiveData<FilterState> = filterSettingsState

    fun setSalary(salary: Int?) {
        var status = filterInteractor.getFilterState()
        val filterState = FilterStatus(status.country, status.area, status.industry, salary, status.onlyWithSalary)
        filterSettingsState.postValue(FilterState.Content(filterState))
    }

    fun setOnlyWithSalary(onlySalary: Boolean?) {
        var status = filterInteractor.getFilterState()
        val filterState = FilterStatus(status.country, status.area, status.industry, status.salary, onlySalary ?: false)
        filterSettingsState.postValue(FilterState.Content(filterState))
    }

    fun getStateAreaAndIndustry() {
        val data = filterInteractor.getFilterState()
        filterSettingsState.postValue(FilterState.Content(data))
        /*
        получаем тут страну город и отрасль, каждый может быть null
        filterSettingsState.postValue(FilterState.Content(country = country, city = city, industry = industry))
        вызываем каждый раз при возврате с экрана выбора места работы и выбора отрасли
         */
    }

    fun getSettingsFilter() {
        // запрос сохраненных данных из sharedPreferences и устанвока в FilterState
        val prefs = filterInteractor.loadFilterFromSharedPreferences()
        filterSettingsState.postValue(
            FilterState.Content(prefs)
        )
    }

    fun saveSettingsFilter() {
        // отправляем настройки в sharedPreferences
        filterInteractor.saveFilterToSharedPreferences(filterInteractor.getFilterState())
    }

    fun resetSettings() {
        filterSettingsState.postValue(
            FilterState.Empty
        )
    }
}
