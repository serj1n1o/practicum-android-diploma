package ru.practicum.android.diploma.favorites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFavoritesBinding
import ru.practicum.android.diploma.favorites.domain.models.FavoriteState
import ru.practicum.android.diploma.favorites.viewmodel.FavoriteVacancyFragmentViewModel
import ru.practicum.android.diploma.global.util.CustomFragment
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.ui.VacancyAdapter

class FavoritesFragment : CustomFragment<FragmentFavoritesBinding>(), VacancyAdapter.VacancyClickListener {

    private val viewModel by viewModel<FavoriteVacancyFragmentViewModel>()
    private var adapter: VacancyAdapter? = null
    private var isClickAllowed = true

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = VacancyAdapter(java.util.ArrayList(), this)
        binding.rvFavorites.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvFavorites.adapter = adapter

        viewModel.observeState().observe(viewLifecycleOwner) { state ->
            render(state)
        }
        viewModel.fillData()
    }

    override fun onVacancyClick(vacancy: Vacancy) {
        if (clickDebounce()) {
            isClickAllowed = true

            // findNavController().navigate(
            // R.id.action_favoriteVacancyFragment_to_vacancyDetailFragment,
            // VacancyDetailFragment.createArgs(vacancy)
            // )
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fillData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

    private fun render(state: FavoriteState) {
        when (state) {
            is FavoriteState.Content -> showContent(state.vacancies)
            is FavoriteState.Empty -> showEmpty()
        }
    }

    private fun showContent(vacancies: List<Vacancy>) {
        binding.apply {
            rvFavorites.isVisible = true
            llFavoritesEmptyPlaceholder.isVisible = false
            llFavoritesNothingFoundPlaceholder.isVisible = false
            rvFavorites.adapter = adapter
            adapter?.vacancyList = vacancies as ArrayList<Vacancy>
            adapter?.notifyDataSetChanged()
        }
    }

    private fun showEmpty() {
        binding.apply {
            rvFavorites.isVisible = false
            llFavoritesEmptyPlaceholder.isVisible = true
        }
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}
