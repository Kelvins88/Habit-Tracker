package com.example.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.habittracker.databinding.FragmentEditHabitBinding
import com.example.habittracker.viewmodel.EditHabitViewModel

class EditHabitFragment : Fragment(), EditHabitListener {
    private lateinit var binding: FragmentEditHabitBinding
    private lateinit var viewModel: EditHabitViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditHabitViewModel::class.java)

        val habitId = EditHabitFragmentArgs.fromBundle(requireArguments()).habitId
        viewModel.fetch(habitId)

        viewModel.habitLD.observe(viewLifecycleOwner) { habit ->
            binding.habit = habit
        }

        binding.listener = this
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onSaveClick(v: View) {
        val updatedHabit = binding.habit
        if (updatedHabit != null) {
            val goalStr = binding.txtEditGoal.text.toString()
            updatedHabit.goal = goalStr.toIntOrNull() ?: updatedHabit.goal

            viewModel.update(updatedHabit)
            Toast.makeText(context, "Habit Updated!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
    }
}