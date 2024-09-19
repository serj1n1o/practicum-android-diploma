package ru.practicum.android.diploma.filter.ui.industry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding
import ru.practicum.android.diploma.filter.domain.model.Industry

open class IndustryAdapter(
    private val items: List<Industry>,
    val itemClickListener: IndustryClickListener
) : RecyclerView.Adapter<IndustryAdapter.RadioViewHolder>() {

    private var selectedPosition = -1
    private var selectedId = "-1"

    inner class RadioViewHolder(val binding: IndustryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val clickHandler: (View) -> Unit = {
            selectedPosition = adapterPosition
            selectedId = items[selectedPosition].id
            itemClickListener.onClick(items[selectedPosition])
            notifyDataSetChanged()
        }

        init {
            binding.apply {
                industryButton.setOnClickListener(clickHandler)
                root.setOnClickListener(clickHandler)
            }
        }
    }

    fun setSelectedVacancyId(id: String) {
        selectedId = id
    }

    override fun getItemCount() = items.size

    operator fun get(position: Int): Industry = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val itemBinding = IndustryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RadioViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.binding.industryButton.isChecked = items[position].id == selectedId
        holder.binding.nameIndustry.text = items[position].name
    }

    fun interface IndustryClickListener {
        fun onClick(industry: Industry)
    }
}
