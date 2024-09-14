package ru.practicum.android.diploma.search.ui.filters.mainfilter

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding
import ru.practicum.android.diploma.global.util.CustomFragment

class FilterSettingsFragment : CustomFragment<FragmentFilterSettingsBinding>() {

    private val viewModel by viewModel<FilterSettingsViewModel>()

    private var salary: Int? = null

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFilterSettingsBinding {
        return FragmentFilterSettingsBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // здесь будем запрашивать данные из sharedPreferences при новом открытии фрагмента
        viewModel.getDataSharedPref()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStateAreaAndIndustry()

        viewModel.getFilterState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is FilterState.Content -> setState(state)
                FilterState.Empty -> {}
            }
        }

        binding.salaryEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                binding.salaryInputLayout.clearFocus()
                viewModel.setSalary(salary)
                true
            } else {
                false
            }
        }

        binding.salaryEditText.doOnTextChanged { text, _, _, _ ->
            salary = text.toString().toInt()
            val hasText = text?.isNotBlank() == true
            binding.salaryInputLayout.defaultHintTextColor = setColorState(hasText)
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnArrowForwardPlace.setOnClickListener {}

        binding.btnArrowForwardIndustry.setOnClickListener {}

        binding.checkOnlySalary.setOnClickListener {
            viewModel.setOnlyWithSalary(binding.checkOnlySalary.isChecked)
        }

    }

    private fun setState(data: FilterState.Content) {
        if (data.onlyWithSalary != null) {
            binding.checkOnlySalary.isChecked = data.onlyWithSalary
        } else {
            binding.checkOnlySalary.isChecked = false
        }

        if (data.salary != null) {
            binding.salaryEditText.setText(data.salary)
        }

        if (data.industry != null) {
            setTextIndustry(data.industry)
        }

        if (data.city != null || data.country != null) {
            setTextPlaceWork(data.country, data.city)
        }
    }

    private fun setTextPlaceWork(country: String?, city: String?) {
        with(binding) {
            val placeWorkText = when {
                !country.isNullOrEmpty() && !city.isNullOrEmpty() -> "$country, $city"
                !country.isNullOrEmpty() -> country
                !city.isNullOrEmpty() -> city
                else -> null
            }
            editTextIndustry.setText(placeWorkText)
            editTextIndustry.isActivated = true
        }
    }

    private fun setTextIndustry(industry: String) {
        with(binding) {
            editTextIndustry.setText(industry)
            editTextIndustry.isActivated = true
        }
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.salaryInputLayout.windowToken, 0)
    }

    private fun setColorState(hasText: Boolean): ColorStateList {
        val colorBlue = requireContext().getColor(R.color.blue)
        val defColor = requireContext().getColor(R.color.editText_text_hint)
        val colorBlack = requireContext().getColor(R.color.black)
        when (hasText) {
            true -> {
                return ColorStateList(
                    arrayOf(
                        intArrayOf(-android.R.attr.state_focused),
                        intArrayOf(android.R.attr.state_focused)
                    ),
                    intArrayOf(colorBlack, colorBlue)
                )
            }

            false -> {
                return ColorStateList(
                    arrayOf(
                        intArrayOf(-android.R.attr.state_focused),
                        intArrayOf(android.R.attr.state_focused)
                    ),
                    intArrayOf(defColor, colorBlue)
                )
            }
        }
    }

}
