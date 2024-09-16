package ru.practicum.android.diploma.global.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class CustomFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    private var isClickAllowed = true

    fun clickDebounce(): Boolean {
        if (isClickAllowed) {
            isClickAllowed = false
            lifecycleScope.launch {
                delay(Constants.DELAY_CLICK)
                isClickAllowed = true
            }
            return true
        }
        return false
    }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = createBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
