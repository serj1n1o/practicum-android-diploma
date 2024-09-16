package ru.practicum.android.diploma.filter.ui.industry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.IndustryItemBinding

abstract class RadioAdapter<T>(private val items: List<T>) : RecyclerView.Adapter<RadioAdapter<T>.RadioViewHolder>() {

    private var selectedPosition = -1

    inner class RadioViewHolder(val binding: IndustryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val clickHandler: (View) -> Unit = {
            selectedPosition = adapterPosition
            notifyDataSetChanged()
        }

        init {
            binding.apply {
                root.setOnClickListener(clickHandler)
                industryButton.setOnClickListener(clickHandler)
            }
        }
    }

    override fun getItemCount() = items.size

    operator fun get(position: Int): T = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RadioViewHolder {
       val b = IndustryItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return RadioViewHolder(b)
        }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        holder.binding.industryButton.isChecked = position == selectedPosition
    }




}
