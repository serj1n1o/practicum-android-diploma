package ru.practicum.android.diploma.filter.ui.industry

import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.global.util.Constants
import ru.practicum.android.diploma.global.util.Mapper
import ru.practicum.android.diploma.search.domain.model.Vacancy

class IndustryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val industryName: TextView = itemView.findViewById(R.id.nameIndustry)
    private val industryButton: RadioButton = itemView.findViewById(R.id.industryeButton)
//    private val vacancyAndCity: TextView = itemView.findViewById(R.id.vacancyAndCity)
//    private val companyName: TextView = itemView.findViewById(R.id.companyName)
//    private val salary: TextView = itemView.findViewById(R.id.salary)

    fun bind(industry: Industry) {
        industryName.text = industry.name
//        vacancyAndCity.text = "${vacancy.name}, ${vacancy.area}"
//        companyName.text = vacancy.name
//        salary.text = vacancy.salary

    }
}
