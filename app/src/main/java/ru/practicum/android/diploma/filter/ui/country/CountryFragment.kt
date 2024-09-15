package ru.practicum.android.diploma.filter.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentCountryBinding
import ru.practicum.android.diploma.filter.domain.sharedata.ShareCountry
import ru.practicum.android.diploma.global.util.CustomFragment

class CountryFragment : CustomFragment<FragmentCountryBinding>() {

    private val viewModel by viewModel<CountryViewModel>()
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCountryBinding {
        return FragmentCountryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleCountry.setOnClickListener {
            findNavController().navigateUp()
        }

        val adapter = CountryAdapter()
        adapter.itemClickListener = { _, item ->
            if (clickDebounce()) {
                viewModel.setCountryInfo(
                    ShareCountry(
                        countryId = item.id, countryName = item.name
                    )
                )
                findNavController().popBackStack()
            }
        }

        binding.rvCountry.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvCountry.adapter = adapter

        viewModel.observeState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is CountryState.Content -> {
                    adapter.countryList.addAll(state.region)
                    adapter.filteredList.addAll(state.region)
                    adapter.notifyDataSetChanged()
                }

                is CountryState.Error -> ""
                is CountryState.Loading -> ""
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
