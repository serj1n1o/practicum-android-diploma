package ru.practicum.android.diploma.filter.ui.industry

import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.model.Industry

class IndustryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val industryName: TextView = itemView.findViewById(R.id.nameIndustry)
    private val industryButton: RadioButton = itemView.findViewById(R.id.industryButton)


    fun bind(industry: Industry, itemClickListener: IndustryAdapter.VacancyClickListener) {
        industryName.text = industry.name
        industryButton.setOnClickListener{
            itemClickListener.onVacancyClick(industry)
        }


    }
}
