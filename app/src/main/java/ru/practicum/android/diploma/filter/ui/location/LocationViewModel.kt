package ru.practicum.android.diploma.filter.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.FilterStatus
import ru.practicum.android.diploma.filter.domain.model.PlaceWork
import ru.practicum.android.diploma.global.util.Mapper

class LocationViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {
    private var sourceCountry: Country? = null
    private var newCountry: Country? = null
    private var oldCountry: Country? = null
    private var oldRegion: Area? = null
    private var newRegion: Area? = null

    private var regionChangedForButtonVisible: Boolean = false
    private var countryChangedForButtonVisible: Boolean = false

    private var sourcePlaceWork: FilterStatus? = null
    private val getSourcePlaceWork get() = sourcePlaceWork

    private val _selectedCountry = MutableLiveData<Country?>()
    val selectedCountry: LiveData<Country?> = _selectedCountry

    private val _countryIsChanged = MutableLiveData<Boolean>()
    val countryIsChanged: LiveData<Boolean> = _countryIsChanged

    private val _placeIsChanged = MutableLiveData<Boolean>()
    val placeIsChanged: LiveData<Boolean> = _placeIsChanged

    private val _selectedRegion = MutableLiveData<Area?>()
    val selectedRegion: LiveData<Area?> = _selectedRegion

    init {
        getFilterState()
    }

    fun comparePlace() {
        _placeIsChanged.value = countryChangedForButtonVisible == true || regionChangedForButtonVisible == true
    }

    fun getDataForCheckHavePlace(): Boolean {
        val placeWork = filterInteractor.getFilterState()
        val country = placeWork.country
        val area = placeWork.area
        if (oldCountry == null) {
            oldCountry = country
        }
        if (oldRegion == null) {
            oldRegion = area
        }

        sourcePlaceWork = placeWork
        return country != null || area != null
    }

    fun setBackPlaceWork() {
        getSourcePlaceWork?.let { filterInteractor.setFilterState(it) }
    }

    fun setNewCountry(country: Country?) {
        newCountry = country
        countryChangedForButtonVisible = oldCountry != newCountry
        _countryIsChanged.value = sourceCountry != newCountry
        sourceCountry = newCountry
    }

    fun setNewRegion(region: Area?) {
        newRegion = region
        regionChangedForButtonVisible = oldRegion != newRegion
        oldRegion = newRegion
    }

    fun getCountryAndRegion() {
        val country = filterInteractor.getFilterState().country
        val area = filterInteractor.getFilterState().area
        _selectedCountry.value = country
        _selectedRegion.value = area
        if (sourceCountry == null) {
            sourceCountry = country
        }
    }

    private fun getFilterState() {
        val filterStatus = filterInteractor.loadFilterFromSharedPreferences()
        _selectedCountry.value = filterStatus.country
        _selectedRegion.value = filterStatus.area
        sourceCountry = filterStatus.country
        oldRegion = filterStatus.area
        oldCountry = filterStatus.country
    }

    fun resetCountry() {
        _selectedCountry.value = null
        _selectedRegion.value = null
        filterInteractor.setListPlaceWork(PlaceWork(countries = null, areas = null))
        setPlaceWork()
    }

    fun resetRegion() {
        _selectedRegion.value = null
        setPlaceWork()
        val countries = filterInteractor.getListPlaceWork().countries
        filterInteractor.setListPlaceWork(PlaceWork(countries = countries, areas = null))
    }

    fun setPlaceWork() {
        val filterStatus = filterInteractor.getFilterState()
        val newFilterStatus = FilterStatus(
            salary = filterStatus.salary,
            industry = filterStatus.industry,
            country = _selectedCountry.value,
            area = _selectedRegion.value,
            onlyWithSalary = filterStatus.onlyWithSalary
        )
        filterInteractor.setFilterState(
            newFilterStatus
        )
    }

    fun setCountryFromRegion(id: String) {
        val listRegions = filterInteractor.getListPlaceWork().areas
        val country = listRegions?.let { Mapper.getCountryByAreaId(id, it) }
        _selectedCountry.postValue(country)
    }
}
