package ru.practicum.android.diploma.vacancy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.practicum.android.diploma.databinding.FragmentVacancyBinding
import ru.practicum.android.diploma.global.util.CustomFragment

class VacancyFragment : CustomFragment<FragmentVacancyBinding>() {

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentVacancyBinding {
        return FragmentVacancyBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val example: TextView = binding.buildConfigReadExampleTextView // пример использования, binding доступен
        clickDebounce() // так же сразу доступен
    }

}
