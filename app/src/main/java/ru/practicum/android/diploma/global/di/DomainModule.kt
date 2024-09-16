package ru.practicum.android.diploma.global.di

import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.domain.api.FavoriteInteractor
import ru.practicum.android.diploma.favorites.domain.impl.FavoriteInteractorImpl
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.impl.SearchInteractorImpl
import ru.practicum.android.diploma.sharing.domain.api.SharingInteractor
import ru.practicum.android.diploma.sharing.domain.impl.SharingInteractorImpl

val domainModule = module {

    factory<SearchInteractor> { SearchInteractorImpl(repository = get()) }

    factory<FavoriteInteractor> {
        FavoriteInteractorImpl(get())
    }

    factory<SharingInteractor> {
        SharingInteractorImpl(externalNavigator = get())
    }
}