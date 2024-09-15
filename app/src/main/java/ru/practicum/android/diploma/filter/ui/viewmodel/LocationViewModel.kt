package ru.practicum.android.diploma.filter.ui.viewmodel

import android.os.NetworkOnMainThreadException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

class LocationViewModel : ViewModel() {
    private val _selectedCountry = MutableLiveData<String>()
    val selectedCountry: LiveData<String> = _selectedCountry

    private val _selectedRegion = MutableLiveData<String>()
    val selectedRegion: LiveData<String> = _selectedRegion

    private val _countries = MutableLiveData<List<String>>() // Список стран
    val countries: LiveData<List<String>> get() = _countries

    private val _regions = MutableLiveData<List<String>>() // Список регионов
    val regions: LiveData<List<String>> get() = _regions

    private val _errorState = MutableLiveData<String?>()
    val errorState: LiveData<String?> get() = _errorState

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            try {
                val countries = listOf("Russia", "China")
                _countries.postValue(countries)
            } catch (e: IOException) {
                _errorState.postValue("Error loading countries: ${e.message}")
            } catch (e: NetworkOnMainThreadException) {
                _errorState.postValue("Network operation on main thread: ${e.message}")
            } catch (e: Throwable) {
                _errorState.postValue("Unexpected error: ${e.message}")
            }
        }
    }

    fun loadRegions(countryId: String) {
        viewModelScope.launch {
            try {
                val regions = listOf("Ural", "Moscow")
                _regions.postValue(regions)
            } catch (e: IOException) {
                _errorState.postValue("Error loading regions: ${e.message}")
            } catch (e: IllegalArgumentException) {
                _errorState.postValue("Invalid country ID: ${e.message}")
            } catch (e: Throwable) {
                _errorState.postValue("Unexpected error: ${e.message}")
            }
        }
    }

    fun onCountrySelected(country: String) {
        _selectedCountry.value = country
        loadRegions(country)
    }

    fun onRegionSelected(region: String) {
        _selectedRegion.value = region
    }
}
