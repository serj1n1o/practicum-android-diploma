package ru.practicum.android.diploma.global.util

import android.content.Context
import ru.practicum.android.diploma.R

fun declineVacancy(context: Context, num: Int): String {
    if (num % NUMBER_100 / NUMBER_10 == 1) {
        return context.getString(R.string.decline_vacancy_11)
    }
    return when (num % NUMBER_10) {
        1 -> context.getString(R.string.decline_vacancy_1)
        in 2..4 -> context.getString(R.string.decline_vacancy_2_4)
        else -> context.getString(R.string.decline_vacancy_default)
    }
}

private const val NUMBER_100 = 100
private const val NUMBER_10 = 100
