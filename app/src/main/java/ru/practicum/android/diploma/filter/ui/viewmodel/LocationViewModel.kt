package ru.practicum.android.diploma.filter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LocationViewModel() : ViewModel() {

    private val _countries = MutableLiveData<List<String>>() // Список стран
    val countries: LiveData<List<String>> get() = _countries

    private val _regions = MutableLiveData<List<String>>() // Список регионов
    val regions: LiveData<List<String>> get() = _regions

    // Состояние для обработки ошибок
    private val _errorState = MutableLiveData<String?>()
    val errorState: LiveData<String?> get() = _errorState

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            try {
            } catch (e: Exception) {
                _errorState.postValue(e.message)
            }
        }
    }

    fun loadRegions(countryId: String) {
        viewModelScope.launch {
            try {
            } catch (e: Exception) {
                _errorState.postValue(e.message)
            }
        }
    }

    // Метод для обработки выбора страны
    fun onCountrySelected(country: String) {
        loadRegions(country)
    }
}
