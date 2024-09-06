package ru.practicum.android.diploma.search.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.global.util.CustomFragment
import ru.practicum.android.diploma.global.util.declineVacancy

class SearchFragment : CustomFragment<FragmentSearchBinding>() {

    private val viewModel by viewModel<SearchViewModel>()
    private val vacancies = ArrayList<SearchViewModel._Vacancy>()
    private lateinit var onVacancyClickDebounce: (SearchViewModel._Vacancy) -> Unit
//    private val adapter = VacansyAdapter(vacancies){ vacancy ->
//        onVacancyClickDebounce(vacancy)
//    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.vacancyList.layoutManager = LinearLayoutManager(requireContext())
//        binding.vacancyList.adapter = adapter
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
            if (!text.isNullOrEmpty()) {
                viewModel.searchDebounce(text.toString())
            }
        }
        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }
        onVacancyClickDebounce = { vacancy->
            if(clickDebounce()) {
//                findNavController().navigate()
            }
        }
    }

    fun render(state: SearchState) {
        when (state) {
            is SearchState.EmptyEditText -> {
                setStateEmptyEditText()
            }

            is SearchState.Content -> {
                setStateContent(state.vacancies)
            }

            is SearchState.NotFound -> {
                setStateNotFound()
            }

            is SearchState.NoConnection -> {
                setStateNoConnection()
            }

            is SearchState.Loading -> {
                setStateLoading()
            }

            is SearchState.EmptyEditTextInFocus -> {
                setStateEmptyTextInFocus()
            }
        }
    }

    private fun setStateEmptyEditText() {
        binding.vacancyList.isVisible = false
        binding.progressBar.isVisible = false
        binding.windowMessage.isVisible = true
        binding.countVacancies.isVisible = false
        binding.textMessage.isVisible = false
        binding.imageMessage.setImageResource(R.drawable.image_man_with_binoculars)
    }

    private fun setStateContent(vacanciesFromVM: List<SearchViewModel._Vacancy>) {
        vacancies.clear()
        vacancies.addAll(vacanciesFromVM)
        binding.vacancyList.isVisible = true
        binding.progressBar.isVisible = false
        binding.windowMessage.isVisible = false
        binding.countVacancies.text = getString(
            R.string.founded_vacancies,
            vacancies.size.toString(),
            declineVacancy(requireContext(), vacancies.size)
        )
        binding.countVacancies.isVisible = true
//                adapter.notifyDataSetChanged()
    }

    private fun setStateNotFound() {
        binding.vacancyList.isVisible = false
        binding.progressBar.isVisible = false
        binding.windowMessage.isVisible = true
        binding.countVacancies.setText(R.string.no_such_vacancies)
        binding.countVacancies.isVisible = true
        binding.textMessage.setText(R.string.unable_to_retrieve_job_listing)
        binding.textMessage.isVisible = true
        binding.imageMessage.setImageResource(R.drawable.image_no_list_vacancy)
    }

    private fun setStateNoConnection() {
        binding.vacancyList.isVisible = false
        binding.progressBar.isVisible = false
        binding.windowMessage.isVisible = true
        binding.countVacancies.isVisible = false
        binding.textMessage.setText(R.string.no_internet)
        binding.textMessage.isVisible = true
        binding.imageMessage.setImageResource(R.drawable.image_no_internet)
    }

    private fun setStateLoading() {
        binding.vacancyList.isVisible = false
        binding.progressBar.isVisible = true
        binding.windowMessage.isVisible = false
        binding.countVacancies.isVisible = false
    }

    private fun setStateEmptyTextInFocus() {
        binding.vacancyList.isVisible = false
        binding.progressBar.isVisible = false
        binding.windowMessage.isVisible = false
        binding.countVacancies.isVisible = false
    }

    private fun renderEditTextIconsVisibility(s: CharSequence?) {
        if (s.isNullOrEmpty()) {
            binding.editTextIconSearch.isVisible = true
            binding.clearButton.isVisible = false
        } else {
            binding.editTextIconSearch.isVisible = false
            binding.clearButton.isVisible = true
        }
    }
}
