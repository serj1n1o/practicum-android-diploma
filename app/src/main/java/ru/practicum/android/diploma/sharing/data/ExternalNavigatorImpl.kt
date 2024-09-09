package ru.practicum.android.diploma.sharing.data

import android.app.Application
import android.content.Intent
import ru.practicum.android.diploma.sharing.domain.api.ExternalNavigator

class ExternalNavigatorImpl(private val application: Application) : ExternalNavigator {

    override fun shareLink(message: String) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(Intent.createChooser(shareIntent, ""))
    }
}
