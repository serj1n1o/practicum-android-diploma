package ru.practicum.android.diploma.filter.ui.industry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.ui.SearchState

class FilterIndustryViewModel(private val searchInteractor: SearchInteractor) : ViewModel() {

    private val stateLiveData = MutableLiveData<SearchState>()
    fun observeState(): LiveData<SearchState> = stateLiveData
    private var industriesList = mutableListOf<Vacancy>()
    private var lastSearchText: String? = null

    private fun renderState(state: SearchState) {
        stateLiveData.postValue(state)
    }

    fun fillData() {

    }



//    private fun processResult(result: RequestResult<VacancyList>) {
//        when (result) {
//            is RequestResult.Success -> {
//                if (!lastSearchText.isNullOrEmpty() && result.data!!.found == 0) {
//                    vacanciesList.clear()
//                    renderState(SearchState.NotFound)
//                } else {
//                    val preparedData = result.data!!
//                    vacanciesList.addAll(preparedData.items)
//                    pages = result.data.pages
//                    currentPage = result.data.page
//                    vacanciesFound = result.data.found
//                    val data = preparedData.copy(items = vacanciesList)
//                    renderState(SearchState.Content(data))
//                }
//            }
//
//            is RequestResult.Error -> {
//                renderState(SearchState.Error(result.error!!, currentPage))
//            }
//        }
//    }
//
//    companion object {
//        private const val SEARCH_DEBOUNCE_DELAY = 2000L
//    }

}
