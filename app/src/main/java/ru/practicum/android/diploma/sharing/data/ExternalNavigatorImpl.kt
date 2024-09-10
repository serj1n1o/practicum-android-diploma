package ru.practicum.android.diploma.sharing.data

import android.app.Application
import android.content.Intent
import ru.practicum.android.diploma.sharing.domain.api.ExternalNavigator

class ExternalNavigatorImpl(private val application: Application) : ExternalNavigator {

    override fun shareLink(message: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        val shareIntent = Intent.createChooser(intent, null)
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        application.startActivity(shareIntent)
    }
}
