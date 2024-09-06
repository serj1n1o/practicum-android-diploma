package ru.practicum.android.diploma.about.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentAboutBinding
import ru.practicum.android.diploma.global.util.CustomFragment

class AboutFragment : CustomFragment<FragmentAboutBinding>() {

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAboutBinding {
        return FragmentAboutBinding.inflate(inflater, container, false)
    }

}
