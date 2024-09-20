package ru.practicum.android.diploma.filter.ui.mainfilter

import android.content.Context
import android.content.res.ColorStateList
import ru.practicum.android.diploma.R

object ColorState {
    fun getColorState(context: Context, hasText: Boolean): ColorStateList {
        val colorBlue = context.getColor(R.color.blue)
        val defColor = context.getColor(R.color.editText_text_hint)
        val colorBlack = context.getColor(R.color.black)
        when (hasText) {
            true -> {
                return ColorStateList(
                    arrayOf(
                        intArrayOf(-android.R.attr.state_focused),
                        intArrayOf(android.R.attr.state_focused)
                    ),
                    intArrayOf(
                        colorBlack,
                        colorBlue
                    )
                )
            }
            false -> {
                return ColorStateList(
                    arrayOf(
                        intArrayOf(
                            -android.R.attr.state_focused
                        ),
                        intArrayOf(
                            android.R.attr.state_focused
                        )
                    ),
                    intArrayOf(
                        defColor,
                        colorBlue
                    )
                )
            }
        }
    }
}
