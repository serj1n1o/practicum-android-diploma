package ru.practicum.android.diploma.filter.ui.industry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.ui.SearchState

class FilterIndustryViewModel(private val searchInteractor: SearchInteractor) : ViewModel() {

    private val stateLiveData = MutableLiveData<ScreenState>()
    fun observeState(): LiveData<ScreenState> = stateLiveData
    private var industriesList = mutableListOf<Industry>()
    private var lastSearchText: String? = null

    private fun renderState(state: ScreenState) {
        stateLiveData.postValue(state)
    }

    fun fillData() {
//        industriesList = searchInteractor.getIndustries()
        industriesList.addAll(
            mutableListOf(
                Industry("01", "Директор"),
                Industry("02", "Официант"),
                Industry("03", "Повар"),
                Industry("04", "Русский репер"),
                Industry("05", "Блогер"),
                Industry("06", "Дегустатор пива"),
                Industry("07", "ААААААА"),
                Industry("08", "Официант1"),
                Industry("09", "Официант2"),
                Industry("10", "Официант3"),
                Industry("11", "Официант4"),
                Industry("12", "Официант5"),
                Industry("13", "Официант6"),
                Industry("14", "Официант77"),
                Industry("15", "Официант78"),
                Industry("16", "йййййййй"),
            )
        )
        renderState(ScreenState.Content(industriesList))
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
