package ru.practicum.android.diploma.vacancy.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class DetailsVacancyViewModel(private val searchInteractor: SearchInteractor) : ViewModel() {

    private val vacancyState = MutableLiveData<VacancyState>(VacancyState.Loading)
    fun getVacancy(): LiveData<VacancyState> = vacancyState

    fun getDetailsVacancy(vacancyId: String) {
        viewModelScope.launch {
            searchInteractor.getDetailsVacancy(vacancyId = vacancyId).collect { result ->
                processResult(result.first, result.second)
            }
        }
    }

    private fun processResult(data: VacancyDetails?, error: Int?) {
        when {
            error != null -> {
                vacancyState.postValue(VacancyState.Error(errorCode = error))
            }

            else -> vacancyState.postValue(data?.let { VacancyState.Content(vacancy = it) })
        }
    }

    fun addToFavorites() {
        val vacancy = if (vacancyState.value is VacancyState.Content) {
            (vacancyState.value as VacancyState.Content).vacancy
        } else {
            null
        }
    }

    fun shareVacancy() {
        val urlVacancy = if (vacancyState.value is VacancyState.Content) {
            (vacancyState.value as VacancyState.Content).vacancy.alternateUrl
        } else {
            null
        }

    }

}
