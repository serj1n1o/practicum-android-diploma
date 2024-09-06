package ru.practicum.android.diploma.global.util

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.util.TypedValue
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.global.util.Constants.NUMBER_1
import ru.practicum.android.diploma.global.util.Constants.NUMBER_10
import ru.practicum.android.diploma.global.util.Constants.NUMBER_2
import ru.practicum.android.diploma.global.util.Constants.NUMBER_4

object Mapper {
    fun mapListTextWithDots(strings: List<String>, bulletGapWidth: Int = 16): SpannableString {
        val formattedText = strings.joinToString(separator = "\n")

        val spannableString = SpannableString(formattedText)

        var start = 0
        for (string in strings) {
            val end = start + string.length
            spannableString.setSpan(
                BulletSpan(bulletGapWidth),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            start = end + 1
        }
        return spannableString
    }

    fun mapRadiusForGlide(context: Context, radius: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            radius,
            context.resources.displayMetrics
        ).toInt()
    }

    fun declineVacancy(context: Context, num: Int): String {
        if (num % Constants.NUMBER_100 / NUMBER_10 == NUMBER_1) {
            return context.getString(R.string.decline_vacancy_11)
        }
        return when (num % NUMBER_10) {
            NUMBER_1 -> context.getString(R.string.decline_vacancy_1)
            in NUMBER_2..NUMBER_4 -> context.getString(R.string.decline_vacancy_2_4)
            else -> context.getString(R.string.decline_vacancy_default)
        }
    }

}
