package ru.practicum.android.diploma.filter.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.model.Country
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

    private fun processResult(countriesList: List<Country>?, errorMessage: Int?) {
        when {
            errorMessage != null -> {
                renderState(
                    CountryState.Error(
                        errorMessage = R.string.server_error
                    )
                )
            }

            else -> {
                renderState(
                    CountryState.Content(
                        region = countriesList!!
                    )
                )
            }
        }
    }

    fun renderState(countryState: CountryState) {
        stateLiveData.postValue(countryState)
    }

    fun setCountryInfo(country: ShareCountry) {
    }
}
