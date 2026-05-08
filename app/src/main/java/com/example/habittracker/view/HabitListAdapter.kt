package com.example.habittracker.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
import com.example.habittracker.databinding.HabitListItemBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitListViewModel

class HabitListAdapter(
    val habitList: ArrayList<Habit>,
    val viewModel: HabitListViewModel
) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    class HabitViewHolder(var binding: HabitListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)

    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        with(holder.binding) {
            txtHabitTitle.text = habit.title
            txtHabitDesc.text = habit.description

            txtProgressValue.text = "${habit.currentProgress} / ${habit.goal} ${habit.unit}"

            progressBarHabit.max = habit.goal
            progressBarHabit.progress = habit.currentProgress

            if (habit.currentProgress >= habit.goal) {
                txtStatus.text = "Completed"
                txtStatus.setBackgroundResource(R.drawable.bg_status_badge_green)
                txtStatus.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.status_completed_text))

            } else {
                txtStatus.text = "In Progress"
                txtStatus.setBackgroundResource(R.drawable.bg_status_badge)
                txtStatus.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.status_in_progress_text))
            }

            when (habit.photoUrl) {
                "Baca" -> imageView.setImageResource(R.drawable.`icon_baca`)
                "Fitness" -> imageView.setImageResource(R.drawable.icon_fitness)
                "Meditasi" -> imageView.setImageResource(R.drawable.icon_meditasi)
                "Water" -> imageView.setImageResource(R.drawable.icon_water)
                else -> imageView.setImageResource(R.mipmap.ic_launcher)
            }
            btnPlus.setOnClickListener { viewModel.updateProgress(habit.id, 1) }
            btnMinus.setOnClickListener { viewModel.updateProgress(habit.id, -1) }
        }
    }

    override fun getItemCount(): Int = habitList.size

    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }
}