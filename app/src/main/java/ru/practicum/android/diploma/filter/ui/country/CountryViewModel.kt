package ru.practicum.android.diploma.filter.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filter.domain.sharedata.ShareCountry

class CountryViewModel : ViewModel() {

    private val stateLiveData = MutableLiveData<CountryState>()
    fun observeState(): LiveData<CountryState> = stateLiveData

    init {
        loadCountry()
    }

    fun loadCountry() {
        TODO("Доработать")
    }


    fun setCountryInfo(country: ShareCountry) {
        TODO("Доработать")
    }
}
