package ru.practicum.android.diploma.filter.ui.industry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.model.Industry

class IndustryAdapter(
    var industries: MutableList<Industry>,
    private var itemClickListener: VacancyClickListener,
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
        holder.bind(industry, itemClickListener)

//        holder.itemView.setOnClickListener {
//            itemClickListener.onVacancyClick(industry)
//        }
    }

    override fun getItemCount(): Int {
        return industries.size
    }

    fun interface VacancyClickListener {
        fun onVacancyClick(industry: Industry)
    }
}
