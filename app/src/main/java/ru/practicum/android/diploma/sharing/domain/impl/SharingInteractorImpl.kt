package ru.practicum.android.diploma.sharing.domain.impl

import ru.practicum.android.diploma.sharing.domain.api.ExternalNavigator
import ru.practicum.android.diploma.sharing.domain.api.SharingInteractor

class SharingInteractorImpl(
    private val externalNavigator: ExternalNavigator,
) : SharingInteractor {

    override fun shareVacancy(vacancyUrl: String) {
        externalNavigator.shareLink(vacancyUrl)
    }
}
