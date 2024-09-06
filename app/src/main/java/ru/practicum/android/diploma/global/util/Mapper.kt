package ru.practicum.android.diploma.global.util

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.util.TypedValue

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
}
