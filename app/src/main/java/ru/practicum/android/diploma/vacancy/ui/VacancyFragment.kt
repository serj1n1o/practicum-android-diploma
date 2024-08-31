package ru.practicum.android.diploma.vacancy.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentVacancyBinding

class VacancyFragment: Fragment(R.layout.fragment_vacancy) {
    private var _binding: FragmentVacancyBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVacancyBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
