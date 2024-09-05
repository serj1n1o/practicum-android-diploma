package ru.practicum.android.diploma.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.global.util.debounce
import kotlin.random.Random

class SearchViewModel : ViewModel() {

    // ********** FOR TEST ***********

    data class _Vacancy(val name: String, val description: String)

    enum class _TypeError {
        NO_CONNECTION
    }

    class LocalSearchInteractor {
        fun searchEXAMPLE(string: String): Flow<Pair<List<_Vacancy>?, _TypeError?>> = flow {
            emit(Pair(listOf(_Vacancy("a", "b"), _Vacancy("c", "d")), null))
//            when (Random.nextInt(1, 3)) {
//                1 -> {
//                    emit(Pair(null, _TypeError.NO_CONNECTION))
//                }
//                2 -> {
//                    emit(Pair(listOf(_Vacancy("a", "b"), _Vacancy("c", "d")), null))
//                }
//                3 -> {
//                    emit(Pair(listOf<_Vacancy>(), null))
//                }
//            }
        }
    }

    // ****************************************

    private val stateLiveData = MutableLiveData<SearchState>(SearchState.EmptyEditText)
    fun observeState(): LiveData<SearchState> = stateLiveData

    private var lastSearchText: String? = null

    private val trackSearchDebounce =
        debounce<String>(SEARCH_DEBOUNCE_DELAY, viewModelScope, true) { changedText ->
            search(changedText)
        }

    fun searchDebounce(changedText: String) {
        if (lastSearchText != changedText) {
            lastSearchText = changedText
            trackSearchDebounce(changedText)
        }
    }

    private fun renderState(state: SearchState) {
        stateLiveData.postValue(state)
    }

    fun search(input: String) {
        renderState(SearchState.Loading)
        viewModelScope.launch {
            LocalSearchInteractor()
                .searchEXAMPLE(input)
                .collect { pair ->
                    processResult(pair.first, pair.second)
                }
        }
    }

    private fun processResult(
        foundVacancies: List<_Vacancy>?,
        typeError: _TypeError?
    ) {
        val vacancies = mutableListOf<_Vacancy>()
        if (foundVacancies != null) {
            vacancies.addAll(foundVacancies)
        }

        when {
            typeError != null -> {
                renderState(SearchState.NoConnection)
            }

            foundVacancies.isNullOrEmpty() -> {
                renderState(SearchState.NotFound)
            }

            else -> {
                renderState(SearchState.Content(vacancies))
            }
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

}

