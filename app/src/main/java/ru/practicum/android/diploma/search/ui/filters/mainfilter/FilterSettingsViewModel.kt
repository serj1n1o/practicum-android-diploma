package ru.practicum.android.diploma.search.ui.filters.mainfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilterSettingsViewModel : ViewModel() {
    private val repository = FilterRepository() // пример, наш репозиторий для хранения фильтров

    private val filterSettingsState = MutableLiveData<FilterState>() // для примера сделал filter state

    fun getFilterState(): LiveData<FilterState> = filterSettingsState

    fun setSalary(salary: Int?) {
        filterSettingsState.postValue(FilterState.Content(salary = salary))
    }

    fun setOnlyWithSalary(onlySalary: Boolean?) {
        filterSettingsState.postValue(FilterState.Content(onlyWithSalary = onlySalary))
    }

    fun getStateAreaAndIndustry() {
        val data = repository.getData()
        /*
        получаем тут страну город и отрасль, каждый может быть null
        filterSettingsState.postValue(FilterState.Content(country = country, city = city, industry = industry))
        вызываем каждый раз при возврате с экрана выбора места работы и выбора отрасли
         */
    }

    fun getDataSharedPref() {
        // запрос сохраненных данных из sharedPreferences и устанвока в FilterState
    }
}

class FilterRepository {
    fun getData() {
        // заглушка просто, удалить весь класс
    }
}
