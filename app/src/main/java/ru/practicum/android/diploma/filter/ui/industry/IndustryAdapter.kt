package ru.practicum.android.diploma.filter.ui.industry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.ui.VacancyViewHolder

class IndustryAdapter(
    var industries: MutableList<Industry>,
//    private var itemClickListener: VacancyClickListener,
) : RecyclerView.Adapter<IndustryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): IndustryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.industry_item, parent, false)
        return IndustryViewHolder(view)
    }

    override fun onBindViewHolder(holder: IndustryViewHolder, position: Int) {
        val industry = industries[position]
        holder.bind(industry)

//        holder.itemView.setOnClickListener {
//            itemClickListener.onVacancyClick(vacancy)
//        }
    }

    override fun getItemCount(): Int {
        return industries.size
    }

//    fun interface VacancyClickListener {
//        fun onVacancyClick(vacancy: Vacancy)
//    }
}
