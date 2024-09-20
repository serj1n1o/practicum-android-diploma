package ru.practicum.android.diploma.filter.ui.mainfilter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding
import ru.practicum.android.diploma.global.util.CustomFragment
import ru.practicum.android.diploma.global.util.debounce

class FilterSettingsFragment : CustomFragment<FragmentFilterSettingsBinding>() {

    private val viewModel by viewModel<FilterSettingsViewModel>()
    private var salary: Int? = null
    private var filterHasPlacework: Boolean = false
    private var filterHasIndustry: Boolean = false
    private var filterIsChanged: Boolean = false

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFilterSettingsBinding {
        return FragmentFilterSettingsBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSettingsFilter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStateAreaAndIndustry()
        viewModel.getFilterState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is FilterState.Content -> renderState(state)
                is FilterState.Empty -> renderStateReset()
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        bindingArrowForwardPlace()
        bindingArrowForwardIndustry()
        bindingCheckOnlySalary()
        bindingApply()
        bindingReset()
        initSalaryEditText()
    }

    private fun initSalaryEditText() {
        binding.salaryEditText.doOnTextChanged { text, _, _, _ ->
            val salaryStr = text.toString()
            if (salaryStr.isNotEmpty()) {
                salary = salaryStr.toInt()
                setSalaryDebounce(salary!!)
            } else {
                setSalaryDebounce(null)
            }

            val hasText = text?.isNotBlank() == true
            binding.btnResetSalary.isVisible = hasText
            binding.salaryInputLayout.defaultHintTextColor = ColorState.getColorState(requireContext(), hasText)
        }

        binding.salaryEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                binding.salaryInputLayout.clearFocus()
                true
            } else {
                false
            }
        }

        binding.btnResetSalary.setOnClickListener {
            viewModel.setSalary(null)
            binding.salaryEditText.clearFocus()
            hideKeyboard()
        }
    }

    private fun bindingArrowForwardPlace() {
        binding.btnArrowForwardPlace.setOnClickListener {
            if (!filterHasPlacework) {
                findNavController().navigate(
                    R.id.action_filterSettingsFragment_to_choosingAPlaceOfWorkFragment
                )
            } else {
                filterIsChanged = true
                viewModel.resetPlaceWorkFilter()
                renderStateResetPlacework()
            }
        }
    }

    private fun bindingArrowForwardIndustry() {
        binding.btnArrowForwardIndustry.setOnClickListener {
            if (!filterHasIndustry) {
                findNavController().navigate(
                    R.id.action_filterSettingsFragment_to_filterIndustryFragment
                )
            } else {
                filterIsChanged = true
                viewModel.resetIndustryFilter()
                renderStateResetIndustry()
            }
        }
    }

    private fun bindingReset() {
        binding.btnReset.setOnClickListener {
            filterHasPlacework = false
            filterHasIndustry = false
            binding.btnArrowForwardIndustry.setImageResource(R.drawable.ic_arrow_forward_24px)
            binding.btnArrowForwardPlace.setImageResource(R.drawable.ic_arrow_forward_24px)
            viewModel.resetSettings()
        }
    }

    private fun bindingApply() {
        binding.btnApply.setOnClickListener {
            viewModel.saveSettingsFilter()
            findNavController().popBackStack()
        }
    }

    private fun bindingCheckOnlySalary() {
        binding.checkOnlySalary.setOnClickListener {
            viewModel.setOnlyWithSalary(binding.checkOnlySalary.isChecked)
        }
    }

    private val setSalaryDebounce = debounce<Int?>(DELAY_SET, lifecycleScope, true) { salary ->
        viewModel.setSalary(salary)
    }

    private fun renderStateReset() {
        with(binding) {
            setTextPlaceWork(country = null, city = null)
            setTextIndustry(industry = null)
            salaryEditText.text = null
            editTextPlaceWork.isActivated = false
            editTextIndustry.isActivated = false
            checkOnlySalary.isChecked = false
            btnReset.isVisible = false
            btnApply.isVisible = false
        }
    }

    private fun renderStateResetPlacework() {
        with(binding) {
            setTextPlaceWork(country = null, city = null)
            filterHasPlacework = false
            editTextPlaceWork.isActivated = false
            btnArrowForwardPlace.setImageResource(R.drawable.ic_arrow_forward_24px)
        }
    }

    private fun renderStateResetIndustry() {
        with(binding) {
            setTextIndustry(industry = null)
            filterHasIndustry = false
            editTextIndustry.isActivated = false
            btnArrowForwardIndustry.setImageResource(R.drawable.ic_arrow_forward_24px)
        }
    }

    private fun renderState(data: FilterState.Content) {
        with(binding) {
            checkOnlySalary.isChecked = data.filterStatus.onlyWithSalary

            if (data.filterStatus.salary != salary) {
                salaryEditText.setText(data.filterStatus.salary?.toString())
            }

            data.filterStatus.industry?.name?.let {
                filterHasIndustry = true
                btnArrowForwardIndustry.setImageResource(R.drawable.ic_close_24px)
                setTextIndustry(it)
            }

            if (data.filterStatus.area != null || data.filterStatus.country != null) {
                filterHasPlacework = true
                btnArrowForwardPlace.setImageResource(R.drawable.ic_close_24px)
                setTextPlaceWork(data.filterStatus.country?.name, data.filterStatus.area?.name)
            }

            btnReset.isVisible = !data.filterStatus.isDefaultParams() || filterIsChanged
            btnApply.isVisible = !data.filterStatus.isDefaultParams() || filterIsChanged
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
            editTextPlaceWork.setText(placeWorkText)
            editTextPlaceWork.isActivated = true
        }
    }

    private fun setTextIndustry(industry: String?) {
        with(binding) {
            editTextIndustry.setText(industry)
            editTextIndustry.isActivated = true
        }
    }

    private fun hideKeyboard() {
        val inputManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.salaryInputLayout.windowToken, 0)
    }

    companion object {
        private const val DELAY_SET = 500L
    }
}
