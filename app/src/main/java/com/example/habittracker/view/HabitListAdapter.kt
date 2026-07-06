package com.example.habittracker.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.databinding.HabitListItemBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitListViewModel

class HabitListAdapter(
    val habitList: ArrayList<Habit>,
    val viewModel: HabitListViewModel
) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>(), HabitCardListener {

    class HabitViewHolder(var binding: HabitListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        holder.binding.habit = habit
        holder.binding.listener = this
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = habitList.size

    fun updateHabitList(newHabitList: List<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

    override fun onEditClick(v: View, habit: Habit) {
        val action = HabitListFragmentDirections.actionHabitListFragmentToEditHabitFragment(habit.id)
        Navigation.findNavController(v).navigate(action)
    }

    override fun onProgressUpdate(habit: Habit, delta: Int) {
        viewModel.updateProgress(habit.id, delta)
    }
}