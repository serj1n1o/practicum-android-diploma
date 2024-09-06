package ru.practicum.android.diploma.vacancy.ui.view

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentVacancyBinding
import ru.practicum.android.diploma.global.util.CustomFragment
import ru.practicum.android.diploma.global.util.Mapper
import ru.practicum.android.diploma.global.util.ResponseCodes
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetails
import ru.practicum.android.diploma.vacancy.ui.viewmodel.DetailsVacancyViewModel
import ru.practicum.android.diploma.vacancy.ui.viewmodel.VacancyState

class VacancyFragment : CustomFragment<FragmentVacancyBinding>() {

    private val viewModel by viewModel<DetailsVacancyViewModel>()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentVacancyBinding {
        return FragmentVacancyBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vacancyId = requireArguments().getString(VACANCY_KEY)

        if (vacancyId != null) {
            viewModel.getDetailsVacancy(vacancyId = vacancyId)
        }

        viewModel.getVacancy().observe(viewLifecycleOwner) { state ->
            when (state) {
                is VacancyState.Content -> showContent(state.vacancy)
                is VacancyState.Error -> showError(state.errorCode)
                is VacancyState.Loading -> showLoading()
            }
        }

        binding.icFavorite.setOnClickListener {
            viewModel.addToFavorites()
        }

        binding.icSharing.setOnClickListener {
            viewModel.shareVacancy()
        }

    }

    private fun showLoading() {
        with(binding) {
            progressBar.isVisible = true
            vacancyInfo.isVisible = false
            placeholder.isVisible = false
            icFavorite.isVisible = false
            icSharing.isVisible = false
        }
    }

    private fun showError(errorCode: Int) {
        with(binding) {
            progressBar.isVisible = false
            vacancyInfo.isVisible = false
            icFavorite.isVisible = true
            icSharing.isVisible = true
        }
        when (errorCode) {
            ResponseCodes.CODE_VACANCY_HAVE_NOT -> {
                with(binding) {
                    placeholder.isVisible = true
                    imgPlaceholder.setImageResource(R.drawable.image_no_vacancy)
                    txtPlaceholder.text = getString(R.string.vacancy_not_found_or_deleted)
                }
            }

            ResponseCodes.CODE_BAD_REQUEST -> {
                with(binding) {
                    placeholder.isVisible = true
                    imgPlaceholder.setImageResource(R.drawable.image_error_server_cat)
                    txtPlaceholder.text = getString(R.string.server_error)
                }
            }
        }

    }

    private fun showContent(vacancy: VacancyDetails) {
        with(binding) {
            icFavorite.isVisible = true
            icSharing.isVisible = true
            placeholder.isVisible = false
            progressBar.isVisible = false
            vacancyInfo.isVisible = true

            tvNameVacancy.text = vacancy.name

            if (vacancy.salary != null) {
                salary.text = vacancy.salary
            } else {
                salary.isVisible = false
            }

            Glide.with(requireContext()).load(vacancy.employerLogo).fitCenter()
                .placeholder(R.drawable.ic_placeholder_32px)
                .transform(RoundedCorners(Mapper.mapRadiusForGlide(requireContext(), EMPLOYER_LOGO_RADIUS)))
                .into(icCompany)

            tvAreaCompany.text = vacancy.area
            if (vacancy.employerName != null) tvNameCompany.text = vacancy.employerName

            if (vacancy.experience != null || vacancy.schedule != null || vacancy.employment != null) {
                experienceContent.text = getString(
                    R.string.experience_employment_schedule,
                    vacancy.experience,
                    vacancy.employment,
                    vacancy.schedule
                )
            } else {
                experienceContent.isVisible = false
                requiredExperience.isVisible = false
            }

            descriptionContent.setText(Html.fromHtml(vacancy.description, Html.FROM_HTML_MODE_COMPACT))

            if (vacancy.keySkills != null) {
                keySkillsContent.text = Mapper.mapListTextWithDots(vacancy.keySkills)
            } else {
                keySkillsContent.isVisible = false
                keySkills.isVisible = false
            }
        }
    }

    companion object {
        private const val EMPLOYER_LOGO_RADIUS = 12f
        private const val VACANCY_KEY = "vacancy key"
        fun createArgs(vacancyId: String): Bundle = bundleOf(VACANCY_KEY to vacancyId)
    }
}
