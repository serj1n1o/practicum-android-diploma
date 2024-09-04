package ru.practicum.android.diploma.search.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.search.ui.SearchViewModel

val viewModelModule = module {

    viewModel{
        SearchViewModel()
    }

}
