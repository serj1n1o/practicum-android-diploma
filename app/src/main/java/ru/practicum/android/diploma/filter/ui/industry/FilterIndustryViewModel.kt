package ru.practicum.android.diploma.filter.ui.industry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.search.domain.api.SearchInteractor

class FilterIndustryViewModel(
    private val searchInteractor: SearchInteractor,
    private val filterInteractor: FilterInteractor
) : ViewModel() {

    private val stateLiveData = MutableLiveData<ScreenState>()
    fun observeState(): LiveData<ScreenState> = stateLiveData

    private var industries = mutableListOf<Industry>()
    private var lastSearchText: String? = null

    private fun renderState(state: ScreenState) {
        stateLiveData.postValue(state)
    }

    fun fillData() {
        renderState(ScreenState.Loading)
//        industriesList = searchInteractor.getIndustries() ...
        industries.addAll(
            mutableListOf(
                Industry("01", "Директор"),
                Industry("02", "Официант"),
                Industry("03", "Повар"),
                Industry("04", "Русский репер"),
                Industry("05", "Блогер"),
                Industry("06", "Дегустатор пива"),
                Industry("07", "Создатель многострочных комментариев, в разветвленных пользовательских сетях"),
                Industry("08", "Официант1 Официант1 Официант1 Официант1 Официант1 Официант1 Официант1 Официант1"),
                Industry("09", "Официант2"),
                Industry("10", "Официант3"),
                Industry("11", "aaaa111"),
                Industry("12", "aaaa222"),
                Industry("13", "aaaa123"),
                Industry("14", "bbbbbbbb"),
                Industry("15", "Официант78"),
                Industry("16", "йййййййй"),
            )
        )
        renderState(ScreenState.Content(industries))
    }

    fun searchDebounce(changedText: String) {
        if (lastSearchText != changedText) {
            lastSearchText = changedText
            search(changedText)
        }
    }

    fun search(input: String) {
        val filteredIndustries = industries.filter {
            it.name.lowercase().startsWith(input.lowercase())
        }
        renderState(ScreenState.Content(filteredIndustries))
    }

    fun setIndustry(industry: Industry?) {}

}
