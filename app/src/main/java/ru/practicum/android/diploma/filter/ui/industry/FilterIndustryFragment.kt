package ru.practicum.android.diploma.filter.ui.industry

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentFilterIndustryBinding
import ru.practicum.android.diploma.global.util.CustomFragment
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.ui.SearchState
import ru.practicum.android.diploma.search.ui.SearchViewModel
import ru.practicum.android.diploma.search.ui.VacancyAdapter

class FilterIndustryFragment : CustomFragment<FragmentFilterIndustryBinding>() {

    private val viewModel by viewModel<SearchViewModel>()
    private val vacancies = mutableListOf<Vacancy>()
    private var onVacancyClickDebounce: ((String) -> Unit)? = null
    private val adapter = VacancyAdapter(vacancies) { vacancy ->
        onVacancyClickDebounce?.let { it(vacancy.id) }
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFilterIndustryBinding {
        return FragmentFilterIndustryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.industryList.adapter = adapter
        binding.industryList.setHasFixedSize(false)
        binding.editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && binding.editText.text.isNullOrEmpty()) {
                render(SearchState.EmptyEditTextInFocus)
            }
        }
        binding.clearButton.setOnClickListener {
            binding.editText.setText("")
            val inputMethodManager =
                context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(binding.clearButton.windowToken, 0)
            binding.editText.clearFocus()
            render(SearchState.EmptyEditText)
        }
        binding.editText.doOnTextChanged { text, start, before, count ->
            renderEditTextIconsVisibility(text)
            viewModel.searchDebounce(text.toString())
        }
        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }
        onVacancyClickDebounce = { vacancyId ->
            if (clickDebounce()) {

            }
        }

    }

    private fun renderEditTextIconsVisibility(s: CharSequence?) {
        with(binding) {
            if (s.isNullOrEmpty()) {
                editTextIconSearch.isVisible = true
                clearButton.isVisible = false
            } else {
                editTextIconSearch.isVisible = false
                clearButton.isVisible = true
            }
        }
    }

    private fun render(state: SearchState, newSearch: Boolean = false) {
        when (state) {
            else -> {

            }
        }
    }


    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(binding.editText.windowToken, 0)
    }
}
