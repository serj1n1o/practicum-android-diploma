package ru.practicum.android.diploma.favorites.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.favorites.domain.api.FavoriteInteractor
import ru.practicum.android.diploma.favorites.domain.models.FavoriteState
import ru.practicum.android.diploma.search.domain.model.Vacancy

class FavoriteVacancyFragmentViewModel(
    private val favoriteInteractor: FavoriteInteractor,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<FavoriteState>()

    init {
        fillData()
    }

    fun observeState(): LiveData<FavoriteState> = stateLiveData

    fun fillData() {
        viewModelScope.launch {
            favoriteInteractor.getVacancies().collect { vacancies ->
                getState(vacancies)
            }
        }
    }

    private fun getState(vacancies: List<Vacancy>) {
        if (vacancies.isEmpty()) {
            stateLiveData.postValue(FavoriteState.Empty)
        } else {
            stateLiveData.postValue(FavoriteState.Content(vacancies))
        }
    }
}
