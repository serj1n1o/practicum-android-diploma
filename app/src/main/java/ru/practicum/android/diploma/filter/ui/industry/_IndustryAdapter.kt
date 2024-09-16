package ru.practicum.android.diploma.filter.ui.industry

import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.filter.ui.industry.IndustryAdapter.VacancyClickListener

class _IndustryAdapter(
    val industries: List<Industry>,
    private var itemClickListener: VacancyClickListener
) : RadioAdapter<Industry>(industries) {
    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.nameIndustry.text = this[position].name
        holder.binding.industryButton.setOnClickListener {
            itemClickListener.onVacancyClick(industries[position])
        }
    }

    fun interface VacancyClickListener {
        fun onVacancyClick(industry: Industry)
    }
}
