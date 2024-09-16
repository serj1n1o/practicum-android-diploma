package ru.practicum.android.diploma.filter.ui.area

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.model.Location

class AreaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val areaText: TextView = itemView.findViewById(R.id.areaName)
    fun bind(location: Location) {
        areaText.text = location.area.name
    }
}
