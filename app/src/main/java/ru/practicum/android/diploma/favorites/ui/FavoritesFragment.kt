package ru.practicum.android.diploma.favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.practicum.android.diploma.databinding.FragmentFilterBinding
import ru.practicum.android.diploma.global.util.CustomFragment

class FavoritesFragment : CustomFragment<FragmentFilterBinding>() {

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFilterBinding {
        return FragmentFilterBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
