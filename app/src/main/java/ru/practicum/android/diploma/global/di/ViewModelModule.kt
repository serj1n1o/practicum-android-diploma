package ru.practicum.android.diploma.global.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.ui.viewmodel.FavoriteVacancyFragmentViewModel
import ru.practicum.android.diploma.search.ui.SearchViewModel
import ru.practicum.android.diploma.search.ui.filters.FilterSettingsViewModel
import ru.practicum.android.diploma.vacancy.ui.viewmodel.DetailsVacancyViewModel

val viewModelModule = module {

    viewModel {
        SearchViewModel(searchInteractor = get())
    }

    viewModel<DetailsVacancyViewModel> {
        DetailsVacancyViewModel(
            searchInteractor = get(),
            favoriteInteractor = get(),
            sharingInteractor = get()
        )
    }

    viewModel<FavoriteVacancyFragmentViewModel> {
        FavoriteVacancyFragmentViewModel(favoriteInteractor = get())
    }

    viewModel<FilterSettingsViewModel> {
        FilterSettingsViewModel()
    }
}
