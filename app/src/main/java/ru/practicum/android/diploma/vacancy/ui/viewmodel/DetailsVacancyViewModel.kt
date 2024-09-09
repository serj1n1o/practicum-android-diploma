package ru.practicum.android.diploma.vacancy.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.favorites.domain.api.FavoriteInteractor
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.sharing.domain.api.SharingInteractor
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails

class DetailsVacancyViewModel(
    private val searchInteractor: SearchInteractor,
    private val favoriteInteractor: FavoriteInteractor,
    private val sharingInteractor: SharingInteractor,
) : ViewModel() {

    private val vacancyState = MutableLiveData<VacancyState>(VacancyState.Loading)
    fun getVacancy(): LiveData<VacancyState> = vacancyState

    fun getDetailsVacancy(vacancyId: String) {
        if (vacancyId.isNotEmpty()) {
            viewModelScope.launch {
                searchInteractor.getDetailsVacancy(vacancyId = vacancyId).collect { result ->
                    processResult(result.first, result.second)
                }
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
        if (vacancyState.value is VacancyState.Content) {
            val vacancy = (vacancyState.value as VacancyState.Content).vacancy
            viewModelScope.launch {
                favoriteInteractor.addVacancy(vacancy)
            }

        }
    }

    fun shareVacancy() {
        val vacancyUrl = (vacancyState.value as? VacancyState.Content)?.vacancy?.id?.let { id ->
            "https://api.hh.ru/vacancies/{vacancy_id}"
        }
        vacancyUrl?.let { sharingInteractor.shareVacancy(it) }
    }
}
