package ru.practicum.android.diploma.vacancy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ItemSkillsBinding

class SkillsAdapter : RecyclerView.Adapter<SkillsViewHolder>() {

    var skills = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        return SkillsViewHolder(ItemSkillsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = skills.size

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        holder.bind(skills[position])
    }
}
