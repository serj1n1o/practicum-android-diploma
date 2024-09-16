package ru.practicum.android.diploma.filter.ui.area

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.domain.model.Location

class AreaAdapter(
    var locations: MutableList<Location>,
    private var itemClickListener: AreaClickListener
) : RecyclerView.Adapter<AreaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.area_list_item, parent, false)
        return AreaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val location = locations[position]
        holder.bind(location)
        holder.itemView.setOnClickListener {
            itemClickListener.onAreaClick(location = location)
        }
    }

    override fun getItemCount(): Int = locations.size

    fun interface AreaClickListener {
        fun onAreaClick(location: Location)
    }
}
