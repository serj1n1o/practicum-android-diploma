package ru.practicum.android.diploma.vacancy.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemSkillsBinding

class SkillsViewHolder(private val binding: ItemSkillsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.txtSkill.text = item
    }
}
