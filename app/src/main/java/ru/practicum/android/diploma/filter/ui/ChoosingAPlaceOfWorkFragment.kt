package ru.practicum.android.diploma.filter.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentChoosingAPlaceOfWorkBinding
import ru.practicum.android.diploma.filter.ui.viewmodel.LocationViewModel
import ru.practicum.android.diploma.global.util.CustomFragment
import ru.practicum.android.diploma.global.util.HideKeyboardUtil

class ChoosingAPlaceOfWorkFragment : CustomFragment<FragmentChoosingAPlaceOfWorkBinding>() {

    private val viewModel by viewModel<LocationViewModel>()
    private var isCountryInputFilled = false

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentChoosingAPlaceOfWorkBinding {
        return FragmentChoosingAPlaceOfWorkBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.arrowForwardCountry.setOnClickListener { }

        binding.arrowForwardRegion.setOnClickListener { }

        binding.edNameCountry.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                HideKeyboardUtil.hideKeyboard(binding.edNameCountry)
                binding.edNameCountry.clearFocus()
                true
            } else {
                false
            }
        }

        binding.edNameCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // пока пусто
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateCountryInputUi(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // пока пусто
            }
        })

        binding.crossCountry.setOnClickListener {
            binding.edNameCountry.setText("")
            // KeyboardUtils.hideKeyboard(binding.edNameCountry)
        }

        binding.btChoose.setOnClickListener { }
    }

    private fun updateCountryInputUi(text: String) {
        isCountryInputFilled = text.isNotEmpty()
        binding.arrowForwardCountry.visibility = if (isCountryInputFilled) View.GONE else View.VISIBLE
        binding.crossCountry.visibility = if (isCountryInputFilled) View.VISIBLE else View.GONE
        binding.btChoose.visibility = if (isCountryInputFilled) View.VISIBLE else View.GONE
    }
}
