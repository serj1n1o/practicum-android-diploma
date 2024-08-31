package ru.practicum.android.diploma.about.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentAboutBinding

class AboutFragment: Fragment(R.layout.fragment_about) {
    private var _binding: FragmentAboutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAboutBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
