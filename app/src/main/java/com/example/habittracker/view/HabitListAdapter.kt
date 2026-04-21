package com.example.habittracker.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.databinding.HabitListItemBinding
import com.example.habittracker.model.Habit

class HabitListAdapter(val habitList: ArrayList<Habit>)
    : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    class HabitViewHolder(var binding: HabitListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.binding.txtHabitTitle.text = habitList[position].title
        holder.binding.txtHabitDesc.text = habitList[position].description
        holder.binding.txtHabitPoints.text = "${habitList[position].points} pts"
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }
}