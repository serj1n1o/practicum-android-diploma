package ru.practicum.android.diploma.global.util

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.util.TypedValue
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.global.util.Constants.NUMBER_1
import ru.practicum.android.diploma.global.util.Constants.NUMBER_10
import ru.practicum.android.diploma.global.util.Constants.NUMBER_2
import ru.practicum.android.diploma.global.util.Constants.NUMBER_4
import ru.practicum.android.diploma.search.data.dto.SalaryDto
import ru.practicum.android.diploma.search.data.dto.details.KeySkillsDto
import java.text.NumberFormat
import java.util.Locale

object Mapper {

    fun mapListTextWithDots(strings: List<String>, bulletGapWidth: Int = 16): SpannableString {
        val spannableString = SpannableStringBuilder()

        for (string in strings) {
            val start = spannableString.length
            spannableString.append(string).append("\n")

            spannableString.setSpan(
                BulletSpan(bulletGapWidth),
                start,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return SpannableString(spannableString.trim())
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

    fun mapSalaryToString(salary: SalaryDto?): String {
        val currencySymbol = when (salary?.currency) {
            "RUB", "RUR" -> "₽"
            "BYR" -> "Br"
            "USD" -> "$"
            "EUR" -> "€"
            "KZT" -> "₸"
            "UAH" -> "₴"
            "AZN" -> "₼"
            "UZS" -> "сум"
            "GEL" -> "₾"
            "KGT" -> "сом"
            else -> ""
        }

        fun formatNumber(value: Int?): String? {
            return value?.let {
                val format = NumberFormat.getNumberInstance(Locale("ru", "RU"))
                format.format(it)
            }
        }

        val from = formatNumber(salary?.from)
        val to = formatNumber(salary?.to)

        return when {
            from != null && to != null -> "От $from до $to $currencySymbol"
            from != null -> "От $from $currencySymbol"
            to != null -> "До $to $currencySymbol"
            else -> "Зарплата не указана"
        }
    }

    fun mapSkillsToStringList(list: List<KeySkillsDto>?): List<String>? {
        return list?.map { it.name }
    }

}
